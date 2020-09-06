package ru.ruthenium74.voteforrestaurant.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ruthenium74.voteforrestaurant.exception.ErrorType;
import ru.ruthenium74.voteforrestaurant.model.Dish;
import ru.ruthenium74.voteforrestaurant.repository.CrudDishRepository;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;
import ru.ruthenium74.voteforrestaurant.web.json.JsonUtil;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.DishTestData.*;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.WRONG_RESTAURANT_ID;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.RESTAURANT2;
import static ru.ruthenium74.voteforrestaurant.TestUtil.readFromJson;
import static ru.ruthenium74.voteforrestaurant.web.restaurant.AdminRestaurantRestController.REST_URL;

public class AdminDishRestControllerTest extends AbstractRestControllerTest {

    @Autowired
    CrudDishRepository dishRepository;

    @Test
    void create() throws Exception {
        Dish newDish = getNew();

        ResultActions resultActions = perform(MockMvcRequestBuilders
                .post(REST_URL + "/" +
                        RESTAURANT1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish))
        ).andExpect(status().isCreated());

        Dish created = readFromJson(resultActions, Dish.class);
        int id = created.getId();
        newDish.setId(id);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.findById(id).orElseThrow(), newDish);
    }

    @Test
    void update() throws Exception {
        Dish updated = getUpdated();

        perform(MockMvcRequestBuilders
                .put(REST_URL + "/" + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
        ).andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(dishRepository.findById(DISH1_ID).orElseThrow(), updated);
    }

    @Test
    void updateWithWrongRestaurantId() throws Exception {
        Dish updated = getUpdated();

        perform(MockMvcRequestBuilders
                .put(REST_URL + "/" + WRONG_RESTAURANT_ID + "/dishes/" + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
        ).andExpect(status().isNotFound()).andExpect(errorType(ErrorType.DATA_NOT_FOUND));

        DISH_MATCHER.assertMatch(dishRepository.findById(DISH1_ID).orElseThrow(), DISH1);
    }

    @Test
    void updateWithWrongRestaurant() throws Exception {
        Dish updated = getUpdated();

        perform(MockMvcRequestBuilders
                .put(REST_URL + "/" + RESTAURANT2.getId() + "/dishes/" + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
        ).andExpect(status().isNotFound()).andExpect(errorType(ErrorType.DATA_NOT_FOUND));

        DISH_MATCHER.assertMatch(dishRepository.findById(DISH1_ID).orElseThrow(), DISH1);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders
                .delete(REST_URL + "/" + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
        ).andExpect(status().isNoContent());

        assertThrows(NoSuchElementException.class, () -> dishRepository.findById(DISH1_ID).orElseThrow());

        perform(MockMvcRequestBuilders
                .delete(REST_URL + "/" + RESTAURANT1_ID + "/dishes/" + DISH1_ID)
        ).andExpect(status().isNotFound()).andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }
}
