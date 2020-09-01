package ru.ruthenium74.voteforrestaurant.web.vote;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ruthenium74.voteforrestaurant.model.Vote;
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;
import ru.ruthenium74.voteforrestaurant.repository.CrudUserRepository;
import ru.ruthenium74.voteforrestaurant.repository.CrudVoteRepository;

import java.util.List;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    public static final String REST_URL = "/rest/restaurants";

    private final CrudVoteRepository voteRepository;
    private final CrudRestaurantRepository restaurantRepository;
    private final CrudUserRepository userRepository;

    public VoteRestController(CrudVoteRepository voteRepository, CrudRestaurantRepository restaurantRepository, CrudUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{id}/votes")
    @Transactional
    public void vote(@PathVariable int id) {
        Vote vote = new Vote(null, userRepository.getOne(100000), restaurantRepository.getOne(id));
        voteRepository.save(vote);
    }

    @GetMapping("/votes")
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }
}
