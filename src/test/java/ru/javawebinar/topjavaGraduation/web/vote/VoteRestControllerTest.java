package ru.javawebinar.topjavaGraduation.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjavaGraduation.model.Vote;
import ru.javawebinar.topjavaGraduation.service.VoteService;
import ru.javawebinar.topjavaGraduation.web.AbstractControllerTest;
import ru.javawebinar.topjavaGraduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjavaGraduation.LunchTestData.LUNCH1_ID;
import static ru.javawebinar.topjavaGraduation.LunchTestData.LUNCH_TODAY_ID;
import static ru.javawebinar.topjavaGraduation.TestUtil.readFromJson;
import static ru.javawebinar.topjavaGraduation.UserTestData.USER_ID;
import static ru.javawebinar.topjavaGraduation.VoteTestDate.*;

class VoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + "/";

    @Autowired
    private VoteService service;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + VOTE1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOTE1));
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER_VOTES));
    }

    @Test
    void getAllByLunch() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "byLunch/" + LUNCH1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(VOTE1));
    }

    @Test
    void createWithLocation() throws Exception {
        Vote newVote = getNew();
        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL + LUNCH_TODAY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote)));

        Vote created = readFromJson(action, Vote.class);
        Integer newId = created.getId();
        newVote.setId(newId);
        assertMatch(created, newVote);
        assertMatch(service.get(newId, USER_ID), newVote);
    }

    @Test
    void update() throws Exception {
        Vote updated = getUpdated();

        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + VOTE_TODAY_ID + "/" + LUNCH_TODAY_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        assertMatch(service.get(VOTE_TODAY_ID, USER_ID), updated);
    }
}