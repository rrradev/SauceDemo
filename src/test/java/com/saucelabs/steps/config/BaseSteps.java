package com.saucelabs.steps.config;

import com.saucelabs.utils.ConfigHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Boolean.parseBoolean;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class BaseSteps {

    protected static ConfigHelper CONFIG = ConfigHelper.getInstance();

    static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    static String ENV_HEADLESS = System.getenv("ENV_HEADLESS");

    protected WebDriver getDriver() {
        return driver.get();
    }

    void setupDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if(parseBoolean(ENV_HEADLESS)) {
            options.addArguments("--headless");
        }
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        driver.set(new ChromeDriver(options));
        driver.get().manage().window().maximize();
    }

    void quitDriver() {
        driver.get().quit();
        driver.remove();
    }

}
