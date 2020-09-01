package ru.ruthenium74.voteforrestaurant.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ruthenium74.voteforrestaurant.DishTestData;
import ru.ruthenium74.voteforrestaurant.RestaurantTestData;
import ru.ruthenium74.voteforrestaurant.model.Dish;
import ru.ruthenium74.voteforrestaurant.repository.CrudDishRepository;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;
import ru.ruthenium74.voteforrestaurant.web.json.JsonUtil;
import ru.ruthenium74.voteforrestaurant.web.restaurant.AdminRestaurantRestController;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.DishTestData.DISH_MATCHER;
import static ru.ruthenium74.voteforrestaurant.TestUtil.readFromJson;

public class AdminDishRestControllerTest extends AbstractRestControllerTest {

    @Autowired
    CrudDishRepository dishRepository;

    @Test
    void create() throws Exception {
        Dish newDish = DishTestData.getNew();

        ResultActions resultActions = perform(MockMvcRequestBuilders
                .post(AdminRestaurantRestController.REST_URL + "/" +
                        RestaurantTestData.RESTAURANT1_ID + "/dishes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish))
        ).andExpect(status().isCreated());

        Dish created = readFromJson(resultActions, Dish.class);
        int id = created.getId();
        newDish.setId(id);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.findById(id).orElseThrow(), newDish);
    }
}
