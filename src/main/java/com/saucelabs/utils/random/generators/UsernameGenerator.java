package com.saucelabs.utils.random.generators;

import com.saucelabs.utils.random.config.IRandomValueGenerator;

public class UsernameGenerator implements IRandomValueGenerator {

    @Override
    public String generate() {
        return faker.name().username();
    }
}
