package com.novianto.restaurant.app.web.vote;

import com.novianto.restaurant.app.model.Vote;
import com.novianto.restaurant.app.service.VoteService;
import com.novianto.restaurant.app.to.VoteResultTo;
import com.novianto.restaurant.app.to.VoteTo;
import com.novianto.restaurant.app.util.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/votes")
    public List<VoteTo> getAllUserVote(@AuthenticationPrincipal @ApiIgnore AuthUser authUser) {
        return voteService.getAll(authUser.id());
    }

    @GetMapping("/votes/{id}")
    public Vote get(@PathVariable UUID id) {
        return voteService.get(id);
    }

    @GetMapping("/restaurants/{restaurantsId}/votes")
    public ResponseEntity<Vote> voteForRestaurant(@PathVariable UUID restaurantId, @AuthenticationPrincipal @ApiIgnore AuthUser authUser) {
        Vote vote = voteService.voteRestaurant(restaurantId, authUser.id());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/../../../votes/{id}")
                .buildAndExpand(vote.getId()).normalize().toUri();

        return ResponseEntity.created(uriOfNewResource).body(vote);
    }

    @GetMapping("/restaurants/{restaurantId}/votes")
    public List<Vote> getTodayVotesByRestaurant(@PathVariable UUID restaurantId) {
        return voteService.getTodayVotesByRestaurant(restaurantId);
    }

    @GetMapping("/restaurants/votes")
    public List<VoteResultTo> getVotingResult() {
        return voteService.getVotingResult();
    }
}
