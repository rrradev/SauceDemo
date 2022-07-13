package com.saucelabs.steps;

import com.saucelabs.enitities.User;
import com.saucelabs.steps.config.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.JavascriptExecutor;
import com.saucelabs.pages.LoginPage;
import com.saucelabs.pages.ProductsPage;

public class LoginSteps extends BaseSteps implements En {

    private LoginPage loginPage;
    private ProductsPage productsPage;

    public LoginSteps() {
        Given("^.* is on the Login Page$", () -> {
            driver.manage().deleteAllCookies();
            driver.get(CONFIG.getUrl());
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
            productsPage = loginPage.logIn(user);
        });

        Then("^.* should see the correct error:$", (DataTable table) -> {
            String error = table.transpose().asMap().get("error");

            Assertions.assertThat(loginPage.readFrom(loginPage.getErrorMsg()))
                    .as("Is correct error message displayed?")
                    .contains(error);
        });

        Then("^.* should see the Products page:$", () -> {
            Assertions.assertThat(productsPage.readFrom(productsPage.getTitle()))
                    .as("Correct title is displayed")
                    .isEqualToIgnoringCase("Products");
        });
    }
}
