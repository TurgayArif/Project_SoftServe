package com.easyrestaurant.tests;

import com.easyrestaurant.core.TestUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class WaiterPanelPageTests extends TestUtils {

    @Parameters({"username", "password"})
    @Description("ID_20:As a waiter I can close orders that have been sent for preparation by me, when they are complete")
    @Test(priority = 5)
    public void canCloseOrder(String username, String password) {
        web.waiterPanelPage().updateDatabaseIfNoOrdersInProgress(username);
        web.homePage().navigateToHomePage();
        Allure.step("Home page is opened");
        web.homePage().clickOnSignIn();
        Allure.step("Sign in page is opened");
        web.signInPage().signIn(username, password);
        Allure.step("Typing username and password and clicking on submit button");
        web.waiterPanelPage().clickOnInProgressNavBar();
        Allure.step("Clicking on In progress in navigation bar");
        var clientTextInProgressMenu = web.waiterPanelPage().getClientInformation();
        web.waiterPanelPage().clickOnDownArrowToExpandOrder()
                             .clickOnCloseOrder()
                             .clickOnHistoryNavBar();
        Allure.step("Click on down arrow to expand the order");
        Allure.step("Click on close order button");
        Allure.step("Click on History in navigation bar");
        var clientTextHistoryMenu = web.waiterPanelPage().getAllClientInfoFromHistory();
        String expectedClientInfo = "";
        for (String s : clientTextHistoryMenu) {
            if (s.equalsIgnoreCase(clientTextInProgressMenu.toLowerCase())) {
                expectedClientInfo = s;
            }
        }
        Assert.assertEquals(clientTextInProgressMenu, expectedClientInfo, "The client information are not equal.");
        Allure.step("Verifying that the client information is presented in history tab");
    }
}
