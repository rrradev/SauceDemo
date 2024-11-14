package com.saucelabs.utils.random.generators;

import com.saucelabs.utils.random.config.RandomValueGenerator;

public class PasswordGenerator implements RandomValueGenerator {

    @Override
    public String generate() {
        return faker.lorem().characters(15, true, true);
    }
}
