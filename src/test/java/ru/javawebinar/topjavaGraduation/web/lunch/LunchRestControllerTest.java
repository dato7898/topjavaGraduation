package ru.javawebinar.topjavaGraduation.web.lunch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjavaGraduation.model.Lunch;
import ru.javawebinar.topjavaGraduation.service.LunchService;
import ru.javawebinar.topjavaGraduation.util.exception.NotFoundException;
import ru.javawebinar.topjavaGraduation.web.AbstractControllerTest;
import ru.javawebinar.topjavaGraduation.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjavaGraduation.TestUtil.readFromJson;
import static ru.javawebinar.topjavaGraduation.TestUtil.readFromJsonMvcResult;
import static ru.javawebinar.topjavaGraduation.LunchTestData.*;

class LunchRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = LunchRestController.REST_URL + '/';
    private static final String REST_URL_ADMIN = REST_URL + "admin/";

    @Autowired
    private LunchService mealService;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + LUNCH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Lunch.class), LUNCH1));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL_ADMIN + LUNCH1_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(LUNCH1_ID));
    }

    @Test
    void update() throws Exception {
        Lunch updated = getUpdated();

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL_ADMIN + LUNCH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(mealService.get(LUNCH1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Lunch newMeal = getNew();
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL_ADMIN)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal)));

        Lunch created = readFromJson(action, Lunch.class);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(mealService.get(newId), newMeal);
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(LUNCHES));
    }

    @Test
    void filter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "filter")
                .param("startDate", "2015-05-30").param("endDate", "2015-05-30"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(LUNCH3, LUNCH2, LUNCH1));
    }

    @Test
    void filterAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "filter?startDate="))
                .andExpect(status().isOk())
                .andExpect(contentJson(LUNCHES));
    }
}
