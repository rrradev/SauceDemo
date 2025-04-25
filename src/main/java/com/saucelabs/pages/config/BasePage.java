package com.saucelabs.pages.config;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public abstract class BasePage {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofMillis(3000L);

    @Autowired
    protected WebDriver driver;
    @Autowired
    private FluentWait<WebDriver> defaultWait;

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element) {
        waitToBeClickable(element);
        element.click();
    }

    public void type(WebElement element, String text) {
        waitToBeClickable(element);
        element.clear();
        element.sendKeys(text);
    }

    public boolean canSee(WebElement element) {
        try {
            waitToBeVisible(element);
        } catch (TimeoutException te) {
            return false;
        }

        return true;
    }

    public String readFrom(WebElement element) {
        waitToBeVisible(element);

        return element.getText();
    }

    protected void waitToBeVisible(WebElement element) {
        defaultWait.until(refreshed(visibilityOf(element)));
    }

    protected void waitToBeClickable(WebElement element) {
        defaultWait.until(refreshed(elementToBeClickable(element)));
    }
}
