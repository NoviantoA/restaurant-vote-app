package com.novianto.restaurant.app.repository;

import com.novianto.restaurant.app.model.Restaurant;
import com.novianto.restaurant.app.model.Vote;
import com.novianto.restaurant.app.repository.jpa.VoteJpaRepository;
import com.novianto.restaurant.app.to.VoteResultTo;
import com.novianto.restaurant.app.to.VoteTo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public class VoteRepository {

    private final VoteJpaRepository voteJpaRepository;

    public VoteRepository(VoteJpaRepository voteJpaRepository) {
        this.voteJpaRepository = voteJpaRepository;
    }

    public Vote save(Vote vote) {
        return voteJpaRepository.save(vote);
    }

    public Vote get(UUID id) {
        return voteJpaRepository.findById(id).orElse(null);
    }

    public Vote getUserTodayVote(UUID userId) {
        return voteJpaRepository.getUserTodayVote(userId, LocalDateTime.of(LocalDate.now(), LocalTime.MIN))
                .orElse(null);
    }

    public List<Vote> getTodayVotesByRestaurant(Restaurant restaurant) {
        return voteJpaRepository.getVotesByRestaurantAndDateTimeIsAfter(restaurant, LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
    }

    public List<VoteTo> getUserVotes(UUID userId) {
        return voteJpaRepository.getUserVote(userId);
    }

    public List<VoteResultTo> getVotingResult() {
        return voteJpaRepository.getVotingResult(LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
    }
}
