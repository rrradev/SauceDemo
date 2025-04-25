package com.saucelabs.context;


import com.saucelabs.driver.DriverProvider;
import io.cucumber.spring.ScenarioScope;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@ComponentScan(basePackages = "com.saucelabs")
public class TestConfig {

    static Duration DEFAULT_TIMEOUT = Duration.ofMillis(3000L);
    static Duration DEFAULT_POLLING = Duration.ofMillis(100L);

    @Bean
    @ScenarioScope
    public WebDriver webDriver() {
        DriverProvider driverProvider = new DriverProvider();
        driverProvider.initDriver();
        return driverProvider.getDriver();
    }

    @Bean
    @ScenarioScope
    public FluentWait<WebDriver> defaultWait(WebDriver driver) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .pollingEvery(DEFAULT_POLLING);
    }

    @Bean
    @ScenarioScope
    public SoftAssertions softAssertions() {
        return new SoftAssertions();
    }
}
