package com.saucelabs.utils.random.config;

import com.github.javafaker.Faker;

public interface RandomValueGenerator {

    Faker faker = Faker.instance();

    String generate();
}
