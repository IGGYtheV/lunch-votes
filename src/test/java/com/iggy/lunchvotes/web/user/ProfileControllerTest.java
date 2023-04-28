package com.iggy.lunchvotes.web.user;

import com.iggy.lunchvotes.model.user.User;
import com.iggy.lunchvotes.repository.UserRepository;
import com.iggy.lunchvotes.to.UserTo;
import com.iggy.lunchvotes.util.UserUtil;
import com.iggy.lunchvotes.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.iggy.lunchvotes.web.user.ProfileController.REST_URL;
import static com.iggy.lunchvotes.web.user.UniqueMailValidator.EXCEPTION_DUPLICATE_EMAIL;
import static com.iggy.lunchvotes.web.user.UserTestData.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProfileControllerTest extends AbstractControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void get() throws Exception {
        performJwt(MockMvcRequestBuilders.get(REST_URL), user)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(USER_MATCHER.contentJson(user));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        performJwt(MockMvcRequestBuilders.delete(REST_URL), user)
                .andExpect(status().isNoContent());
        USER_MATCHER.assertMatch(userRepository.findAll(), admin);
    }

    @Test
    void update() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", USER_MAIL, "newPassword");
        performJwt(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(ru.javaops.cloudjava.util.JsonUtil.writeValue(updatedTo)), user)
                .andDo(print())
                .andExpect(status().isNoContent());

        USER_MATCHER.assertMatch(userRepository.getExisted(USER_ID), UserUtil.updateFromTo(new User(user), updatedTo));
    }

    @Test
    void updateInvalid() throws Exception {
        UserTo updatedTo = new UserTo(null, null, "password", null);
        performJwt(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ru.javaops.cloudjava.util.JsonUtil.writeValue(updatedTo)), user)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateDuplicate() throws Exception {
        UserTo updatedTo = new UserTo(null, "newName", ADMIN_MAIL, "newPassword");
        performJwt(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(ru.javaops.cloudjava.util.JsonUtil.writeValue(updatedTo)), user)
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string(containsString(EXCEPTION_DUPLICATE_EMAIL)));
    }
}