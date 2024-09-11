package com.saucelabs.pages.components;

import com.saucelabs.pages.config.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class Header extends BasePage {

    @FindBy(id = "shopping_cart_container")
    WebElement cartBtn;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
