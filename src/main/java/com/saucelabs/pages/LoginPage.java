package com.saucelabs.pages;

import com.saucelabs.context.PageObj;
import com.saucelabs.enitities.User;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.saucelabs.pages.config.BasePage;

@Getter
@PageObj
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    WebElement usernameFld;

    @FindBy(id = "password")
    WebElement passwordFld;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMsg;

    public void logIn(User user) {
        type(usernameFld, user.getUsername());
        type(passwordFld, user.getPassword());
        click(loginBtn);
    }
}
