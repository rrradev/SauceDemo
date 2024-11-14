package com.saucelabs.pages.components;

import com.saucelabs.context.PageObj;
import com.saucelabs.pages.config.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
@PageObj
public class Header extends BasePage {

    @FindBy(id = "shopping_cart_container")
    WebElement cartBtn;

}
