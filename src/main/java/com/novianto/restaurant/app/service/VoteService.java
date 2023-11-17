package com.novianto.restaurant.app.service;

import com.novianto.restaurant.app.model.Restaurant;
import com.novianto.restaurant.app.model.User;
import com.novianto.restaurant.app.model.Vote;
import com.novianto.restaurant.app.repository.RestaurantRepository;
import com.novianto.restaurant.app.repository.UserRepository;
import com.novianto.restaurant.app.repository.VoteRepository;
import com.novianto.restaurant.app.to.VoteResultTo;
import com.novianto.restaurant.app.to.VoteTo;
import com.novianto.restaurant.app.util.exception.AlreadyVoteException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.novianto.restaurant.app.util.TimeLimitUtil.canChangeTodayVote;
import static com.novianto.restaurant.app.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public Vote voteRestaurant(UUID restaurantId, UUID userId) {
        Restaurant restaurant = restaurantRepository.getRef(restaurantId);
        User user = userRepository.getRef(userId);
        Vote vote = voteRepository.getUserTodayVote(userId);

        if (vote == null) {
            vote = new Vote(user, restaurant, LocalDateTime.now());
        } else if (canChangeTodayVote()) {
            vote.setRestaurant(restaurant);
            vote.setDateTime(LocalDateTime.now());
        } else {
            throw new AlreadyVoteException("User dengan id = " + userId + " sudah vote. Voting hanya dapat diubah hingga pukul 11:00");
        }
        return voteRepository.save(vote);
    }

    public List<Vote> getTodayVotesByRestaurant(UUID restaurantId) {
        return voteRepository.getTodayVotesByRestaurant(restaurantRepository.get(restaurantId));
    }

    public List<VoteResultTo> getVotingResult() {
        return voteRepository.getVotingResult();
    }

    public List<VoteTo> getAll(UUID userId) {
        return voteRepository.getUserVotes(userId);
    }

    public Integer getNumberOfVotesForRestaurant(UUID restaurantId) {
        List<Vote> votes = voteRepository.getTodayVotesByRestaurant(restaurantRepository.get(restaurantId));
        return votes.size();
    }

    public Vote get(UUID id) {
        return checkNotFoundWithId(voteRepository.get(id), id);
    }
}
