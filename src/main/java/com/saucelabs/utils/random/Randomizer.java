package com.saucelabs.utils.random;

import com.saucelabs.utils.random.config.RandomValueGeneratorFactory;

public class Randomizer {

    public static String random(String expression) {
        if (!RandomValueGeneratorFactory.generatorExists(expression)) {
            return expression;
        }

        return RandomValueGeneratorFactory.getGenerator(expression).generate();
    }
}
