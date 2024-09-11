package com.saucelabs.steps;

import com.saucelabs.enitities.Item;
import com.saucelabs.pages.CartPage;
import com.saucelabs.pages.ProductsPage;
import com.saucelabs.steps.config.BaseSteps;
import io.cucumber.java8.En;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class CartSteps extends BaseSteps implements En {

    private ProductsPage productsPage;
    private CartPage cartPage;
    private Item randomItemAdded;

    public CartSteps() {

        When("^.* adds an item to .* cart$", () -> {
            productsPage = new ProductsPage(getDriver());
            randomItemAdded = productsPage.addRandomItemToCart();
        });

        When("^.* navigates? to the Cart page$", () -> {
            productsPage = new ProductsPage(getDriver());
            productsPage.click(productsPage.getHeader().getCartBtn());
        });

        Then("^.* should see the item in .* cart$", () -> {
            cartPage = new CartPage(getDriver());

            assertThat(cartPage.getItems())
                    .as("Added item and item in cart match")
                    .hasSize(1)
                    .usingRecursiveComparison()
                    .isEqualTo(Collections.singletonList(randomItemAdded));
        });

        Then("^the cart page should be empty$", () -> {
            cartPage = new CartPage(getDriver());

            assertThat(cartPage.isEmpty())
                    .as("Cart is empty")
                    .isTrue();
        });
    }
}
