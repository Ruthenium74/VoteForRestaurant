package ru.ruthenium74.voteforrestaurant.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ruthenium74.voteforrestaurant.exception.ErrorType;
import ru.ruthenium74.voteforrestaurant.model.Dish;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.DishTestData.DISHES_OF_RESTAURANT1;
import static ru.ruthenium74.voteforrestaurant.DishTestData.DISH_MATCHER;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.WRONG_RESTAURANT_ID;
import static ru.ruthenium74.voteforrestaurant.TestUtil.readListFromJsonMvcResult;
import static ru.ruthenium74.voteforrestaurant.web.dish.DishRestController.DISH_REST_URL;

public class DishRestControllerTest extends AbstractRestControllerTest {
    @Test
    void getAll() throws Exception {
        ResultActions resultActions = perform(MockMvcRequestBuilders
                .get(DISH_REST_URL + "/" + RESTAURANT1_ID + "/dishes")
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<Dish> dishes = readListFromJsonMvcResult(resultActions.andReturn(), Dish.class);

        DISH_MATCHER.assertMatch(dishes, DISHES_OF_RESTAURANT1);
    }

    @Test
    void getAllWithWrongRestaurantId() throws Exception {
        perform(MockMvcRequestBuilders
                .get(DISH_REST_URL + "/" + WRONG_RESTAURANT_ID + "/dishes")
        ).andExpect(status().isNotFound()).andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }
}
