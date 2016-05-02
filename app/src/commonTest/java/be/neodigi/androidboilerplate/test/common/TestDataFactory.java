package be.neodigi.androidboilerplate.test.common;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import be.neodigi.androidboilerplate.data.model.User;

/**
 * Factory class that makes instances of data models with random field values.
 * The aim of this class is to help setting up test fixtures.
 */
public class TestDataFactory {

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static User makeUser(String uniqueSuffix) {
        User u = new User();
        u.setId(1);
        u.setName(uniqueSuffix);
        u.setEmail(uniqueSuffix);
        u.setUsername(uniqueSuffix);
        return u;
    }

    public static List<User> makeListUsers(int number) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            users.add(makeUser(String.valueOf(i)));
        }
        return users;
    }
}