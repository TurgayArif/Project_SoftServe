package com.easyrestaurant.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePageObject {

    @FindBy(css = "a[href='/restaurants']")
    WebElement restaurantsListButtonSelector;
    @FindBy(xpath = "//span[text()='Sign In']")
    WebElement signInSelector;

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public RestaurantsPage clickOnRestaurantList() {
        click(restaurantsListButtonSelector);
        return new RestaurantsPage(driver, log);
    }

    public HomePage clickOnSignIn() {
        click(signInSelector);
        return this;
    }

    public HomePage navigateToHomePage() {
        navigateTo(URL);
        return this;
    }
}
