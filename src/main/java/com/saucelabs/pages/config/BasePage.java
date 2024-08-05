package com.saucelabs.pages.config;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofMillis(3000L);

    protected WebDriver driver;
    private WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    protected void waitToBeVisible(WebElement element) {
        wait.pollingEvery(Duration.ofMillis(100L))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.visibilityOf(element)
                ));
    }

    protected void waitToBeClickable(WebElement element) {
        wait.pollingEvery(Duration.ofMillis(100L))
                .until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(element)
                ));
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

    public void waitForPageToLoad() {
        ExpectedCondition<Boolean> expectation = driver -> {
            assert driver != null;
            return ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState")
                    .toString()
                    .equals("complete");
        };

        try {
            Thread.sleep(200L);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(expectation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
