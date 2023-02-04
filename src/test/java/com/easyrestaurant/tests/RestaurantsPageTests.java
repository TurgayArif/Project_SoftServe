package com.easyrestaurant.tests;

import com.easyrestaurant.core.TestUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RestaurantsPageTests extends TestUtils {

    @Description("ID_27:As an user I can delete dish from the menu")
    @Test(priority = 6)
    @Parameters({"username", "password"})
    public void canDeleteDishFromOrder(String username, String password) {
        web.homePage().navigateToHomePage();
        Allure.step("Home page is opened");
        web.homePage().clickOnSignIn();
        Allure.step("Sign In page is opened");
        web.signInPage().signIn(username, password);
        Allure.step("Typing " + username +" as username and " + password + " for password and clicking submit");
        web.homePage().clickOnRestaurantList();
        Allure.step("Clicking on restaurant list");
        web.restaurantsPage().clickOnFirstWatchMenu()
                .clickOnFirstAddToCartButton();
        takeScreenshot("Added to cart");
        Allure.step("Clicking on first watch menu button and first add to cart button (right arrow)");
        var clientSnackBarText = web.restaurantsPage().getClientSnackBarText();
        Assert.assertEquals(clientSnackBarText, "Item was added", "The client snack bar text are not equal!");
        Allure.step("Verifying that the item was added to the cart");
        web.restaurantsPage().hoverOverOrderCartItem();
        web.restaurantsPage().clickRemoveItemFromCart();
        takeScreenshot("Removed from cart");
        Allure.step("Clicking on remove item from cart button");
        var removedItemSnackBarText = web.restaurantsPage().getClientSnackBarText();
        Assert.assertEquals(removedItemSnackBarText, "Item was removed", "Removed item text are not equal!");
        Allure.step("Verifying that the item was removed from the cart");
    }
    
}
