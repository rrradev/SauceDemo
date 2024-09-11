package com.saucelabs.steps;

import com.saucelabs.enitities.Item;
import com.saucelabs.enitities.User;
import com.saucelabs.pages.CartPage;
import com.saucelabs.pages.LoginPage;
import com.saucelabs.pages.ProductsPage;
import com.saucelabs.steps.config.BaseSteps;
import io.cucumber.java8.En;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;

import java.util.Collections;

public class CartSteps extends BaseSteps implements En {

    private ProductsPage productsPage;
    private CartPage cartPage;
    private Item randomItemAdded;

    public CartSteps() {
        Given("^.* is logged in$", () -> {
            driver.manage().deleteAllCookies();
            driver.get(CONFIG.getUrl());
            ((JavascriptExecutor) driver).executeScript("window.localStorage.clear();");
            User user = new User(CONFIG.getUsername(), CONFIG.getPassword());
            productsPage = new LoginPage(driver).logIn(user);
        });

        When("^.* adds an item to .* cart$", () -> {
            randomItemAdded = productsPage.addRandomItemToCart();
        });

        When("^.* navigates? to the Cart page$", () -> {
            productsPage.click(productsPage.getHeader().getCartBtn());
        });

        Then("^.* should see the item in .* cart$", () -> {
            cartPage = new CartPage(driver);

            Assertions.assertThat(cartPage.getItems())
                    .as("Added item and item in cart match")
                    .hasSize(1)
                    .usingRecursiveComparison()
                    .isEqualTo(Collections.singletonList(randomItemAdded));
        });

        Then("^the cart page should be empty$", () -> {
            cartPage = new CartPage(driver);

            Assertions.assertThat(cartPage.isEmpty())
                    .as("Cart is empty")
                    .isTrue();
        });
    }
}
