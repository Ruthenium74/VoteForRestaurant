package ru.ruthenium74.voteforrestaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ruthenium74.voteforrestaurant.RestaurantTestData;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;
import ru.ruthenium74.voteforrestaurant.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.RESTAURANT_MATCHER;
import static ru.ruthenium74.voteforrestaurant.TestUtil.readFromJson;

public class AdminRestaurantRestControllerTest extends AbstractRestControllerTest {

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = RestaurantTestData.getNew();

        ResultActions resultActions = perform(MockMvcRequestBuilders.post(AdminRestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated());

        Restaurant created = readFromJson(resultActions, Restaurant.class);
        int id = created.getId();
        newRestaurant.setId(id);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(id).orElseThrow(), newRestaurant);
    }
}
