package com.saucelabs.steps.config.hooks;

import io.cucumber.java8.LambdaGlue;
import io.cucumber.java8.Scenario;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import static com.saucelabs.driver.DriverUtils.takesScreenshot;

public class Hooks implements LambdaGlue {

    @Autowired
    WebDriver driver;

    public Hooks() {
        After((Scenario scenario) -> {
            tryToTakeScreenshot(scenario);
            driver.quit();
        });
    }

    @SneakyThrows
    private void tryToTakeScreenshot(Scenario scenario) {
        byte[] screenshot = takesScreenshot(driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Screenshot");
    }
}
