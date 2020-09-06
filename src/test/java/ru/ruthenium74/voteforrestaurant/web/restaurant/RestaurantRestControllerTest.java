package ru.ruthenium74.voteforrestaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.RESTAURANTS;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.RESTAURANT_MATCHER;
import static ru.ruthenium74.voteforrestaurant.TestUtil.readListFromJsonMvcResult;
import static ru.ruthenium74.voteforrestaurant.web.restaurant.RestaurantRestController.REST_URL;

public class RestaurantRestControllerTest extends AbstractRestControllerTest {

    @Test
    void getAllWithDishes() throws Exception {
        ResultActions resultActions = perform(MockMvcRequestBuilders
                .get(REST_URL)
                .param("withDishes","true")
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<Restaurant> restaurants = readListFromJsonMvcResult(resultActions.andReturn(), Restaurant.class);

        RESTAURANT_MATCHER.assertMatch(restaurants, RESTAURANTS);
    }
}
