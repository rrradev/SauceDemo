package com.saucelabs.utils.random.generators;

import com.saucelabs.utils.random.config.IRandomValueGenerator;

public class PasswordGenerator implements IRandomValueGenerator {

    @Override
    public String generate() {
        return faker.lorem().characters(15, true, true);
    }
}
