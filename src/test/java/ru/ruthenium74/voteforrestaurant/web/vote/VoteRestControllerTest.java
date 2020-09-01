package ru.ruthenium74.voteforrestaurant.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ruthenium74.voteforrestaurant.RestaurantTestData;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteRestControllerTest extends AbstractRestControllerTest {

    @Test
    void vote() throws Exception {
        perform(MockMvcRequestBuilders.post(VoteRestController.REST_URL + "/" +
                RestaurantTestData.RESTAURANT1_ID + "/votes"))
                .andExpect(status().isCreated());
    }
}
