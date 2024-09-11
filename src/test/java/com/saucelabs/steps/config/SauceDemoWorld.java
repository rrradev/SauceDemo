package com.saucelabs.steps.config;

import com.saucelabs.enitities.Item;
import io.cucumber.guice.ScenarioScoped;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@ScenarioScoped
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SauceDemoWorld {

    Item itemInCart;
}
