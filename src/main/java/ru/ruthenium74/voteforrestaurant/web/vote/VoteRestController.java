package ru.ruthenium74.voteforrestaurant.web.vote;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.ruthenium74.voteforrestaurant.AuthorizedUser;
import ru.ruthenium74.voteforrestaurant.model.Vote;
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;
import ru.ruthenium74.voteforrestaurant.repository.CrudUserRepository;
import ru.ruthenium74.voteforrestaurant.repository.CrudVoteRepository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteRestController {
    public static final String REST_URL = "/rest/restaurants";

    private final CrudVoteRepository voteRepository;
    private final CrudRestaurantRepository restaurantRepository;
    private final CrudUserRepository userRepository;

    private static final Logger log = getLogger(VoteRestController.class);

    public VoteRestController(CrudVoteRepository voteRepository, CrudRestaurantRepository restaurantRepository, CrudUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/{id}/votes")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void vote(@PathVariable int id) {
        Vote vote = new Vote(null, userRepository.getOne(AuthorizedUser.getAuthorizedUserId()),
                restaurantRepository.getOne(id));

        log.info("Save new vote from user with id={} for restaurant with id={}",
                AuthorizedUser.getAuthorizedUserId(), id);

        voteRepository.save(vote);
    }

    @GetMapping("/votes")
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }
}
