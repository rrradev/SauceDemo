package com.saucelabs.pages;

import com.github.javafaker.Faker;
import com.saucelabs.context.PageObj;
import com.saucelabs.enitities.Item;
import com.saucelabs.pages.config.BasePage;
import com.saucelabs.pages.components.Header;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.saucelabs.pages.components.ItemCard.*;

@Getter
@PageObj
public class ProductsPage extends BasePage {

    @Autowired
    Header header;

    @FindBy(className = "title")
    WebElement title;

    @FindBy(className = "inventory_item")
    WebElement itemCard;

    @FindBy(className = "inventory_item")
    List<WebElement> itemCards;

    public Item addRandomItemToCart() {
        waitToBeVisible(itemCard);

        WebElement randomItemCard = itemCards.get(
                new Faker().number().numberBetween(0, itemCards.size() - 1)
        );

        String itemName = readFrom(randomItemCard.findElement(ITEM_NAME));
        String itemDesc = readFrom(randomItemCard.findElement(ITEM_DESC));
        String itemPrice = readFrom(randomItemCard.findElement(ITEM_PRICE));

        click(randomItemCard.findElement(ADD_TO_CART_BTN));

        return Item.builder()
                .name(itemName)
                .desc(itemDesc)
                .price(itemPrice)
                .build();
    }
}
