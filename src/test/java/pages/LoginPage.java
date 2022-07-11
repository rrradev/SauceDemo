package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.config.BasePage;

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

    public WebElement getUsernameFld() {
        return usernameFld;
    }

    public WebElement getPasswordFld() {
        return passwordFld;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }
}
