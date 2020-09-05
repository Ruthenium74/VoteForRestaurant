package ru.ruthenium74.voteforrestaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.ruthenium74.voteforrestaurant.exception.ErrorType;
import ru.ruthenium74.voteforrestaurant.model.Restaurant;
import ru.ruthenium74.voteforrestaurant.repository.CrudRestaurantRepository;
import ru.ruthenium74.voteforrestaurant.web.AbstractRestControllerTest;
import ru.ruthenium74.voteforrestaurant.web.json.JsonUtil;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ruthenium74.voteforrestaurant.RestaurantTestData.*;
import static ru.ruthenium74.voteforrestaurant.TestUtil.readFromJson;
import static ru.ruthenium74.voteforrestaurant.util.ValidationUtil.RESTAURANT_UNIQUE_NAME_CONSTRAINT;

public class AdminRestaurantRestControllerTest extends AbstractRestControllerTest {

    @Autowired
    private CrudRestaurantRepository restaurantRepository;

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = getNew();

        ResultActions resultActions = perform(MockMvcRequestBuilders.post(AdminRestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant))
        ).andExpect(status().isCreated());

        Restaurant created = readFromJson(resultActions, Restaurant.class);
        int id = created.getId();
        newRestaurant.setId(id);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(id).orElseThrow(), newRestaurant);
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void createDuplicate() throws Exception {
        Restaurant newRestaurant = getNew();

        perform(MockMvcRequestBuilders.post(AdminRestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andExpect(status().isCreated());

        perform(MockMvcRequestBuilders.post(AdminRestaurantRestController.REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant))
        ).andExpect(status().isConflict())
                .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                .andExpect(detailMessage(RESTAURANT_UNIQUE_NAME_CONSTRAINT));
    }

    @Test
    void update() throws Exception {
        Restaurant updated = getUpdated();

        perform(MockMvcRequestBuilders
                .put(AdminRestaurantRestController.REST_URL + "/" + updated.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(getUpdated()))
        ).andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(restaurantRepository.findById(updated.getId()).orElseThrow(), updated);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders
                .delete(AdminRestaurantRestController.REST_URL + "/" + RESTAURANT1_ID)
        ).andExpect(status().isNoContent());

        assertThrows(NoSuchElementException.class,
                () -> restaurantRepository.findById(RESTAURANT1_ID).orElseThrow());

        perform(MockMvcRequestBuilders
                .delete(AdminRestaurantRestController.REST_URL + "/" + RESTAURANT1_ID)
        ).andExpect(status().isNotFound()).andExpect(errorType(ErrorType.DATA_NOT_FOUND));
    }
}
