package com.saucelabs.utils.random.config;

import lombok.SneakyThrows;

public class RandomValueGeneratorFactory {

    @SneakyThrows
    public static RandomValueGenerator getGenerator(String expression) {
        return Generator.getGeneratorClass(expression).getDeclaredConstructor().newInstance();
    }

    public static boolean generatorExists(String expression) {
        return expression != null && Generator.generatorExists(expression);
    }

}
