package com.saucelabs.context;


import com.saucelabs.driver.DriverProvider;
import io.cucumber.spring.ScenarioScope;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.saucelabs")
public class TestConfig {

    @Bean
    @ScenarioScope
    public WebDriver webDriver() {
        DriverProvider driverProvider = new DriverProvider();
        driverProvider.initDriver();
        return driverProvider.getDriver();
    }

    @Bean
    @ScenarioScope
    public SoftAssertions softAssertions() {
        return new SoftAssertions();
    }
}
