package com.saucelabs.pages.components;

import org.openqa.selenium.By;

public class ItemCard {

    public static final By ITEM_NAME = By.cssSelector("div.inventory_item_name");
    public static final By ITEM_DESC = By.cssSelector("div.inventory_item_desc");
    public static final By ITEM_PRICE = By.xpath(".//div[@class='inventory_item_price']");
    public static final By ADD_TO_CART_BTN = By.cssSelector("button.btn_inventory");

}
