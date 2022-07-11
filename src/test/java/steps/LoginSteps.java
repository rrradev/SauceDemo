package steps;

import enitities.User;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import pages.LoginPage;
import pages.ProductsPage;
import steps.config.BaseSteps;

public class LoginSteps extends BaseSteps implements En {

    LoginPage loginPage;
    ProductsPage productsPage;

    public LoginSteps() {
        Given("^.* is on the Login Page$", () -> {
            driver.manage().deleteAllCookies();
            driver.get("https://www.saucedemo.com/");
            ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
            loginPage = new LoginPage(driver);
        });

        Then("^.* should see the login form$", () -> {
            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(loginPage.canSee(loginPage.getUsernameFld()))
                    .as("Is username field displayed?")
                    .isTrue();
            softly.assertThat(loginPage.canSee(loginPage.getPasswordFld()))
                    .as("Is password field displayed?")
                    .isTrue();
            softly.assertThat(loginPage.canSee(loginPage.getLoginBtn()))
                    .as("Is login button displayed?")
                    .isTrue();
            softly.assertAll();
        });

        When("^.* logs? in with .* credentials:$", (User user) -> {
            loginPage.type(loginPage.getUsernameFld(), user.getUsername());
            loginPage.type(loginPage.getPasswordFld(), user.getPassword());
            loginPage.click(loginPage.getLoginBtn());
        });

        Then("^.* should see the correct error:$", (DataTable table) -> {
            String error = table.transpose().asMap().get("error");

            Assertions.assertThat(loginPage.readFrom(loginPage.getErrorMsg()))
                    .as("Is correct error message displayed?")
                    .contains(error);
        });

        Then("^.* should see the Products page:$", () -> {
            productsPage = new ProductsPage(driver);

            Assertions.assertThat(productsPage.readFrom(productsPage.getTitle()))
                    .as("Correct title is displayed")
                    .isEqualToIgnoringCase("Products");
        });
    }
}
