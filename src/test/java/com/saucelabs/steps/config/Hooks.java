package com.saucelabs.steps.config;

import io.cucumber.java8.LambdaGlue;
import io.cucumber.java8.Scenario;
import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks extends BaseSteps implements LambdaGlue {

    public Hooks() {
        Before((Scenario scenario) -> {
            setupDriver();
        });

        After((Scenario scenario) -> {
            tryToTakeScreenshot(scenario);
            quitDriver();
        });
    }

    @SneakyThrows
    private void tryToTakeScreenshot(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Screenshot");
    }
}
