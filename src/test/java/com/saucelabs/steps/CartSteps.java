package com.saucelabs.steps;

import com.google.inject.Inject;
import com.saucelabs.enitities.Item;
import com.saucelabs.pages.CartPage;
import com.saucelabs.pages.ProductsPage;
import com.saucelabs.steps.config.BaseSteps;
import com.saucelabs.steps.config.SauceDemoWorld;
import io.cucumber.java8.En;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartSteps extends BaseSteps implements En {

    @Inject
    SauceDemoWorld sauceDemoWorld;
    ProductsPage productsPage;
    CartPage cartPage;

    public CartSteps() {

        When("^.* adds an item to .* cart$", () -> {
            productsPage = new ProductsPage(getDriver());
            final Item itemInCart = productsPage.addRandomItemToCart();
            sauceDemoWorld.setItemInCart(itemInCart);
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
                    .isEqualTo(Collections.singletonList(sauceDemoWorld.getItemInCart()));
        });

        Then("^the cart page should be empty$", () -> {
            cartPage = new CartPage(getDriver());

            assertThat(cartPage.isEmpty())
                    .as("Cart is empty")
                    .isTrue();
        });
    }
}
