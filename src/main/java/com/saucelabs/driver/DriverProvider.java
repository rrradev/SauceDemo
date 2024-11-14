package com.saucelabs.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.Boolean.parseBoolean;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DriverProvider {

    static final String ENV_HEADLESS = System.getenv("ENV_HEADLESS");

    @Getter
    WebDriver driver;

    public void initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if (parseBoolean(ENV_HEADLESS)) {
            options.addArguments("--headless");
        }
        options.addArguments("--disable-search-engine-choice-screen");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

}
