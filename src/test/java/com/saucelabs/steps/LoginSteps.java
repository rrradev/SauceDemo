package com.saucelabs.steps;

import com.saucelabs.enitities.User;
import com.saucelabs.steps.config.BaseSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java8.En;
import org.assertj.core.api.SoftAssertions;
import com.saucelabs.pages.LoginPage;
import com.saucelabs.pages.ProductsPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginSteps extends BaseSteps implements En {

    @Autowired
    private LoginPage loginPage;
    @Autowired
    private ProductsPage productsPage;

    public LoginSteps() {

        Given("^.* is on the Login Page$", () -> {
            driver.get(CONFIG.getUrl());
        });

        Given("^.* is logged in$", () -> {
            driver.get(CONFIG.getUrl());
            User user = User.builder()
                    .username(CONFIG.getUsername())
                    .password(CONFIG.getPassword())
                    .build();
            loginPage.logIn(user);
        });

        When("^.* logs? in with .* credentials:$", (User user) -> {
            loginPage.logIn(user);
        });

        Then("^.* should see the login form$", () -> {
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

        Then("^.* should see the correct error:$", (DataTable table) -> {
            String error = table.transpose().asMap().get("error");

            assertThat(loginPage.readFrom(loginPage.getErrorMsg()))
                    .as("Is correct error message displayed?")
                    .contains(error);
        });

        Then("^.* should see the Products page:$", () -> {
            assertThat(productsPage.readFrom(productsPage.getTitle()))
                    .as("Correct title is displayed")
                    .isEqualToIgnoringCase("Products");
        });
    }
}
