package com.saucelabs.steps.config;

import com.saucelabs.utils.random.Randomizer;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java8.LambdaGlue;

import java.lang.reflect.Type;
import java.util.Map;

public class DataTableTypes implements LambdaGlue {

    private final static ObjectMapper mapper = new ObjectMapper();

    public DataTableTypes() {
        DefaultDataTableEntryTransformer("[empty]", (Map<String, String> tableRow, Type toValueType) -> {
            for (Map.Entry<String, String> entry : tableRow.entrySet()) {
                String value = entry.getValue();
                String randomValue = Randomizer.random(value);
                if (!value.equals(randomValue)) {
                    tableRow.replace(entry.getKey(), randomValue);
                }
            }

            return mapper.convertValue(tableRow, mapper.constructType(toValueType));
        });
    }
}
