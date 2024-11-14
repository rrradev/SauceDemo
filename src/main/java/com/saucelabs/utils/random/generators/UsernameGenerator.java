package com.saucelabs.utils.random.generators;

import com.saucelabs.utils.random.config.RandomValueGenerator;

public class UsernameGenerator implements RandomValueGenerator {

    @Override
    public String generate() {
        return faker.name().username();
    }
}
