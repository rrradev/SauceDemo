package com.saucelabs.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import com.github.javafaker.Faker;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SauceFaker {

    final static String PREFIX = "$random";

    final static String USERNAME = "Username";
    final static String PASSWORD = "Password";

    final static Faker faker = Faker.instance();

    public static String random(String expression) {
        String randomValue;

        if (!expression.startsWith(PREFIX)) {
            return expression;
        }

        switch (expression) {
            case PREFIX + USERNAME:
                randomValue = faker.name().username();
                break;
            case PREFIX + PASSWORD:
                randomValue = faker.lorem().characters(15, true, true);
                break;
            default:
                throw new IllegalArgumentException("No such expression: " + expression);
        }

        return randomValue;
    }
}
