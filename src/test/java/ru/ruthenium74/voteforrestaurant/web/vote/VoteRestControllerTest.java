package ru.ruthenium74.voteforrestaurant.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.ruthenium74.voteforrestaurant.RestaurantTestData;
import ru.ruthenium74.voteforrestaurant.exception.ErrorType;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.util.ValidationUtil.ONE_VOTE_PER_DAY_CONSTRAINT;

public class VoteRestControllerTest extends AbstractRestControllerTest {

    @Test
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL + "/" +
                RestaurantTestData.RESTAURANT1_ID + "/votes"))
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void doubleVote() throws Exception {
        perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL + "/" +
                RestaurantTestData.RESTAURANT1_ID + "/votes"))
                .andExpect(status().isCreated());

        perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL + "/" +
                RestaurantTestData.RESTAURANT1_ID + "/votes"))
                .andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andExpect(detailMessage(ONE_VOTE_PER_DAY_CONSTRAINT));
    }
}
