package ru.javawebinar.topjavaGraduation.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javawebinar.topjavaGraduation.UserTestData;
import ru.javawebinar.topjavaGraduation.model.User;
import ru.javawebinar.topjavaGraduation.service.UserService;
import ru.javawebinar.topjavaGraduation.web.AbstractControllerTest;
import ru.javawebinar.topjavaGraduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjavaGraduation.UserTestData.*;
import static ru.javawebinar.topjavaGraduation.web.users.ProfileRestController.REST_URL;

public class ProfileRestControllerTest extends AbstractControllerTest {
    @Autowired
    private UserService userService;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), ADMIN);
    }

    @Test
    void update() throws Exception {
        User updated = UserTestData.getUpdated();
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(userService.get(USER_ID), updated);
    }
}
