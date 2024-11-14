package com.saucelabs.driver;

import lombok.SneakyThrows;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.aop.framework.Advised;

public class DriverUtils {

    public static TakesScreenshot takesScreenshot(WebDriver driver) {
        driver = unwrap(driver);

        return ((TakesScreenshot) driver);
    }

    public static JavascriptExecutor javascriptExecutor(WebDriver driver) {
        driver = unwrap(driver);

        return ((JavascriptExecutor) driver);
    }

    @SneakyThrows
    private static WebDriver unwrap(WebDriver driver) {
        if (driver instanceof Advised) {
            driver = (WebDriver) ((Advised) driver).getTargetSource().getTarget();
        }

        return driver;
    }
}
