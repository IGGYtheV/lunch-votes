package com.iggy.lunchvotes.util;

import com.iggy.lunchvotes.model.user.Role;
import com.iggy.lunchvotes.model.user.User;
import com.iggy.lunchvotes.to.UserTo;
import lombok.experimental.UtilityClass;

import static com.iggy.lunchvotes.config.LoginSecurityConfiguration.PASSWORD_ENCODER;


@UtilityClass
public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}