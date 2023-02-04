package com.easyrestaurant.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RestaurantsPage extends BasePageObject {

    @FindBy(xpath = "//span[contains(text(), 'Watch Menu')]")
    List<WebElement> watchMenuSelector;
    @FindBy(css = "button[aria-label='Add to cart']")
    List<WebElement> addToCartButtonSelector;
    @FindBy(id = "client-snackbar")
    WebElement clientSnackBarSelector;
    @FindBy(css = "button[aria-label='Remove item']")
    WebElement removeItemButtonSelector;
    @FindBy(css = "[class*='OrderCartItem-mask']")
    WebElement orderCartItem;
    @FindBy(xpath = "//button[@aria-label='Show cart']")
    WebElement showCartButton;

    public RestaurantsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    //Click on first Watch Menu
    public RestaurantsPage clickOnFirstWatchMenu() {
        for (WebElement e : watchMenuSelector) {
            e.click();
            break;
        }
        return this;
    }

    //Click on first Add to cart button
    public RestaurantsPage clickOnFirstAddToCartButton() {
        for (WebElement e : addToCartButtonSelector) {
            if (e.isDisplayed() && e.isEnabled()) {
                e.click();
                break;
            } else {
                click(showCartButton);
                hoverOverOrderCartItem();
                clickRemoveItemFromCart();
            }
        }
        return this;
    }


    //Get client snackbar text
    public String getClientSnackBarText() {
        return getText(clientSnackBarSelector);
    }

    //Hover over order cart item
    public RestaurantsPage hoverOverOrderCartItem() {
        Actions a = new Actions(driver);
        a.moveToElement(orderCartItem).perform();
        return this;
    }

    //Click on Remove item from cart button
    public RestaurantsPage clickRemoveItemFromCart() {
        click(removeItemButtonSelector);
        return this;
    }


}
