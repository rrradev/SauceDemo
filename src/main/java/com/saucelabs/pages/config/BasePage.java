package com.saucelabs.pages.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private static final Duration DEFAULT_WAIT_DURATION = Duration.ofMillis(3000);
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitToBeVisible(WebElement element, Duration interval) {
        final WebDriverWait wait = new WebDriverWait(driver, interval);
        wait.pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOf(element)
                ));
    }

    public void waitToBeVisible(WebElement element) {
        waitToBeVisible(element, DEFAULT_WAIT_DURATION);
    }

    public void waitToBeClickable(WebElement element, Duration interval) {
        final WebDriverWait wait = new WebDriverWait(driver, interval);
        wait.pollingEvery(Duration.ofMillis(100))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(element)
                ));
    }

    public void click(WebElement element) {
        waitToBeClickable(element, DEFAULT_WAIT_DURATION);
        element.click();
    }

    public void type(WebElement element, String text) {
        waitToBeClickable(element, DEFAULT_WAIT_DURATION);
        element.clear();
        element.sendKeys(text);
    }

    public boolean canSee(WebElement element) {
        try {
            waitToBeVisible(element, DEFAULT_WAIT_DURATION);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public String readFrom(WebElement element) {
        waitToBeVisible(element, DEFAULT_WAIT_DURATION);

        return element.getText();
    }
}
