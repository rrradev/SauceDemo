package com.saucelabs.steps.config;

import com.saucelabs.steps.config.datastore.SauceDemoWorld;
import com.saucelabs.utils.ConfigHelper;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSteps {

    protected static final ConfigHelper CONFIG = ConfigHelper.getInstance();

    @Autowired
    protected SauceDemoWorld sauceDemoWorld;
    @Autowired
    protected WebDriver driver;
    @Autowired
    protected SoftAssertions softly;

}
