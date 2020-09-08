package ru.ruthenium74.voteforrestaurant.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ruthenium74.voteforrestaurant.exception.ErrorType;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.DishTestData.*;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.WRONG_RESTAURANT_ID;
import static ru.ruthenium74.voteforrestaurant.web.dish.DishRestController.DISH_REST_URL;

public class DishRestControllerTest extends AbstractRestControllerTest {
    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders
                .get(DISH_REST_URL + "/" + RESTAURANT1_ID + "/dishes")
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISHES_OF_RESTAURANT1));
    }

    @Test
    void getAllWithWrongRestaurantId() throws Exception {
        perform(MockMvcRequestBuilders
                .get(DISH_REST_URL + "/" + WRONG_RESTAURANT_ID + "/dishes")
        ).andExpect(status().isNotFound()).andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders
                .get(DISH_REST_URL + "/" + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(DISH1));
    }

    @Test
    void getWithWrongRestaurant() throws Exception {
        perform(MockMvcRequestBuilders
                .get(DISH_REST_URL + "/" + WRONG_RESTAURANT_ID + "/dishes/" + DISH1_ID)
        ).andExpect(status().isNotFound())
                .andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }

    @Test
    void getWithWrongId() throws Exception {
        perform(MockMvcRequestBuilders
                .get(DISH_REST_URL + "/" + RESTAURANT1_ID + "/dishes/" + WRONG_DISH_ID)
        ).andExpect(status().isNotFound())
                .andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }
}
