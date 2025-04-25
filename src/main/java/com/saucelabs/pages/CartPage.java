package com.saucelabs.pages;

import com.saucelabs.context.PageObj;
import com.saucelabs.enitities.Item;
import com.saucelabs.pages.config.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.saucelabs.pages.components.ItemCard.*;

@PageObj
public class CartPage extends BasePage {

    @FindBy(className = "cart_item")
    WebElement itemCard;

    @FindBy(className = "cart_item")
    List<WebElement> itemCards;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();

        if(isEmpty()) {
            return items;
        }

        for (WebElement itemCard : itemCards) {
            String itemName = readFrom(itemCard.findElement(ITEM_NAME));
            String itemDesc = readFrom(itemCard.findElement(ITEM_DESC));
            String itemPrice = readFrom(itemCard.findElement(ITEM_PRICE));

            Item item = Item.builder()
                    .name(itemName)
                    .desc(itemDesc)
                    .price(itemPrice)
                    .build();

            items.add(item);
        }

        return items;
    }

    public boolean isEmpty() {
        waitToBeVisible(checkoutButton);

        return !canSee(itemCard);
    }
}
