package com.saucelabs.utils.random.config;

import com.saucelabs.utils.random.generators.PasswordGenerator;
import com.saucelabs.utils.random.generators.UsernameGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Arrays;
import java.util.function.Predicate;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Generator {

    USERNAME("$randomUsername", UsernameGenerator.class),
    PASSWORD("$randomPassword", PasswordGenerator.class);

    String expression;
    Class<? extends RandomValueGenerator> generatorClass;

    protected static Class<? extends RandomValueGenerator> getGeneratorClass(String expression) {
        return Arrays.stream(values())
                .filter(generatorWithExpression(expression))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such expression: " + expression))
                .generatorClass;
    }

    protected static boolean generatorExists(String expression) {
        return Arrays.stream(values()).anyMatch(generatorWithExpression(expression));
    }

    private static Predicate<Generator> generatorWithExpression(String expression) {
        return values -> values.expression.equals(expression);
    }
}
