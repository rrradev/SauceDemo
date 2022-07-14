package com.saucelabs.pages;

import com.saucelabs.enitities.Item;
import com.saucelabs.pages.config.Header;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.saucelabs.pages.config.ItemContainer.*;

public class CartPage extends Header {

    @FindBy(className = "cart_item")
    WebElement itemContainer;

    @FindBy(className = "cart_item")
    List<WebElement> itemContainers;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();

        try {
            waitToBeVisible(itemContainer);
        } catch (TimeoutException te) {
            return items;
        }

        for (WebElement targetElement : itemContainers) {
            String itemName = readFrom(targetElement.findElement(ITEM_NAME));
            String itemDesc = readFrom(targetElement.findElement(ITEM_DESC));
            String itemPrice = readFrom(targetElement.findElement(ITEM_PRICE));

            Item item = new Item.ItemBuilder(itemName)
                    .desc(itemDesc)
                    .price(itemPrice)
                    .build();
            items.add(item);
        }

        return items;
    }

    public boolean isEmpty() {
        waitForPageToLoad();

        return !canSee(itemContainer);
    }
}
