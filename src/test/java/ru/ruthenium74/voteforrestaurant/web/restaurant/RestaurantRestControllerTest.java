package ru.ruthenium74.voteforrestaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ruthenium74.voteforrestaurant.exception.ErrorType;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.DishTestData.DISHES_OF_RESTAURANT1;
import static ru.ruthenium74.voteforrestaurant.DishTestData.DISH_MATCHER;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.*;
import static ru.ruthenium74.voteforrestaurant.TestUtil.readFromJsonMvcResult;
import static ru.ruthenium74.voteforrestaurant.TestUtil.readListFromJsonMvcResult;
import static ru.ruthenium74.voteforrestaurant.web.restaurant.RestaurantRestController.REST_URL;

public class RestaurantRestControllerTest extends AbstractRestControllerTest {

    @Test
    void getAllWithDishes() throws Exception {
        ResultActions resultActions = perform(MockMvcRequestBuilders
                .get(REST_URL)
                .param("withDishes", "true")
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<Restaurant> restaurants = readListFromJsonMvcResult(resultActions.andReturn(), Restaurant.class);

        RESTAURANT_MATCHER.assertMatch(restaurants, RESTAURANTS);

        DISH_MATCHER.assertMatch(restaurants.get(0).getDishes(), DISHES_OF_RESTAURANT1);
    }

    @Test
    void getAll() throws Exception {
        ResultActions resultActions = perform(MockMvcRequestBuilders
                .get(REST_URL)
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<Restaurant> restaurants = readListFromJsonMvcResult(resultActions.andReturn(), Restaurant.class);

        RESTAURANT_MATCHER.assertMatch(restaurants, RESTAURANTS);

        assertThrows(NullPointerException.class, () -> restaurants.get(0).getDishes().get(0));
    }

    @Test
    void getWithDishes() throws Exception {
        ResultActions resultActions = perform(MockMvcRequestBuilders
                .get(REST_URL + "/" + RESTAURANT1_ID)
                .param("withDishes", "true")
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Restaurant restaurant = readFromJsonMvcResult(resultActions.andReturn(), Restaurant.class);

        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT1);

        DISH_MATCHER.assertMatch(restaurant.getDishes(), DISHES_OF_RESTAURANT1);
    }

    @Test
    void get() throws Exception {
        ResultActions resultActions = perform(MockMvcRequestBuilders
                .get(REST_URL + "/" + RESTAURANT1_ID)
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        Restaurant restaurant = readFromJsonMvcResult(resultActions.andReturn(), Restaurant.class);

        RESTAURANT_MATCHER.assertMatch(restaurant, RESTAURANT1);

        assertThrows(NullPointerException.class, () -> restaurant.getDishes().get(0));
    }

    @Test
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders
        .get(REST_URL + "/" + WRONG_RESTAURANT_ID)
        ).andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }
}
