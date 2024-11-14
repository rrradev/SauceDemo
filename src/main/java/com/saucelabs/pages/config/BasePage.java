package com.saucelabs.pages.config;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Duration;

import static com.saucelabs.driver.DriverUtils.javascriptExecutor;
import static org.openqa.selenium.support.ui.ExpectedConditions.refreshed;

public abstract class BasePage {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofMillis(3000L);

    @Autowired
    protected WebDriver driver;
    private WebDriverWait wait;

    @PostConstruct
    private void init() {
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    protected void waitToBeVisible(WebElement element) {
        wait.pollingEvery(Duration.ofMillis(100L))
                .until(refreshed(ExpectedConditions.visibilityOf(element)));
    }

    protected void waitToBeClickable(WebElement element) {
        wait.pollingEvery(Duration.ofMillis(100L))
                .until(refreshed(ExpectedConditions.elementToBeClickable(element)));
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
        ExpectedCondition<Boolean> expectation = driver ->
                (javascriptExecutor(driver))
                        .executeScript("return document.readyState")
                        .toString()
                        .equals("complete");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.pollingEvery(Duration.ofMillis(500L))
                    .until(expectation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
