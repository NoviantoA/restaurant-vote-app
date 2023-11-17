package com.novianto.restaurant.app.repository.jpa;

import com.novianto.restaurant.app.model.Restaurant;
import com.novianto.restaurant.app.model.Vote;
import com.novianto.restaurant.app.to.VoteResultTo;
import com.novianto.restaurant.app.to.VoteTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VoteJpaRepository extends JpaRepository<Vote, UUID> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id = :id")
    UUID delete(UUID id);

    @Query("SELECT new com.novianto.restaurant.app.to.VoteTo(v.id, v.dateTime, v.user.id, v.restaurant.id) " +
            "FROM Vote v " +
            "WHERE v.user.id = :userId")
    List<VoteTo> getUserVote(@Param("userId") UUID userId);

    @Query("SELECT new com.novianto.restaurant.app.to.VoteResultTo(r.id, r.name, COUNT(r.id)) " +
            "FROM Restaurant r LEFT OUTER JOIN Vote v ON r.id = v.restaurant.id " +
            "WHERE v.dateTime > :currentDate " +
            "GROUP BY r.id, r.name " +
            "ORDER BY COUNT(r.id) DESC")
    List<VoteResultTo> getVotingResult(LocalDateTime currentDate);

    @Query("SELECT v FROM Vote v WHERE v.user.id = :userId AND v.dateTime > :presentDay")
    Optional<Vote> getUserTodayVote(UUID userId, LocalDateTime presentDay);

    List<Vote> getVotesByRestaurantAndDateTimeIsAfter(Restaurant restaurant, LocalDateTime date);
}
