package com.easyrestaurant.tests;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.easyrestaurant.core.TestUtils;

public class PositiveSignInPageTests extends TestUtils{

    @Parameters({"username", "password"})
    @Description("Positive log in test")
    @Test(priority = 2)
    public void signInWithValidCredentials(String username, String password) {
        web.homePage().navigateToHomePage();
        Allure.step("Opened home page.");
        web.homePage().clickOnSignIn();
        Allure.step("Opened Sign In page.");
        takeScreenshot("Opened Sign In page");
        web.signInPage().signIn(username, password, "/moderator");
        takeScreenshot("Opened");
        Allure.step("Type username and password and clicking submit button.");
        var currUrl = web.moderatorPanelPage().getCurrentURL();
        Assert.assertEquals(currUrl, "http://localhost:3000/moderator");
        Allure.step("Verifying that the current url is http://localhost:3000/moderator");
    }
    
}
