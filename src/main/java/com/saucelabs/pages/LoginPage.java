package com.saucelabs.pages;

import com.saucelabs.enitities.User;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.saucelabs.pages.config.BasePage;

@Getter
public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    WebElement usernameFld;

    @FindBy(id = "password")
    WebElement passwordFld;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement errorMsg;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductsPage logIn(User user) {
        type(usernameFld, user.getUsername());
        type(passwordFld, user.getPassword());
        click(loginBtn);

        return new ProductsPage(driver);
    }
}
