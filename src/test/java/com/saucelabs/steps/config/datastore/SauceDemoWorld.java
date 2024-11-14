package com.saucelabs.steps.config.datastore;

import com.saucelabs.enitities.Item;
import io.cucumber.spring.ScenarioScope;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Data
@Component
@ScenarioScope
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SauceDemoWorld {

    Item itemInCart;
}
