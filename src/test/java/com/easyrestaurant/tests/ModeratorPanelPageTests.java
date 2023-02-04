package com.easyrestaurant.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.easyrestaurant.core.TestUtils;

public class ModeratorPanelPageTests extends TestUtils {


    @Parameters({"username", "password"})
    @Description("ID_13:As a moderator I can approve added restaurant")
    @Test(priority = 3)
    public void canApproveRestaurant(String username, String password) {
        web.moderatorPanelPage().updateRestaurantsStatusInDataBase();
        web.homePage().navigateToHomePage();
        Allure.step("Home page is opened.");
        web.homePage().clickOnSignIn();
        Allure.step("Sign In page is opened");
        web.signInPage().signIn(username, password);
        Allure.step("Typing username and password and clicking submit button.");
        web.moderatorPanelPage().clickOnNavBarButton("unapproved");
        Allure.step("Clicking on \"Unapproved\" navigation bar button.");
        web.moderatorPanelPage().approveRestaurant("Brown-Glenn");
        Allure.step("Clicking on Approve button");
        var snackBarText = web.moderatorPanelPage().getSnackBarText();
        Assert.assertEquals(snackBarText, "approved");
        Allure.step("Verifying that the snack bar text is approved");
        web.moderatorPanelPage().updateRestaurantsStatusInDataBase();
    }

    @Parameters({"username", "password"})
    @Description("ID_14:As a moderator I can disapprove restaurant")
    @Test(priority = 4)
    public void canDisapproveRestaurant(String username, String password) {
        web.moderatorPanelPage().updateRestaurantsStatusInDataBase();
        web.homePage().navigateToHomePage();
        Allure.step("Home page is opened");
        web.homePage().clickOnSignIn();
        Allure.step("Sign In page is opened");
        web.signInPage().signIn(username, password);
        Allure.step("Typing username and password and clicking submit button.");
        web.moderatorPanelPage().clickOnNavBarButton("unapproved");
        Allure.step("Clicking on \"Unapproved\" navigation bar button.");
        web.moderatorPanelPage().disapproveRestaurant("Wyatt, Baker and Chapman");
        Allure.step("Clicking on Disapprove button");
        var snackBarText = web.moderatorPanelPage().getSnackBarText();
        Assert.assertEquals(snackBarText, "disapproved");
        Allure.step("Verifying that the snack bar text is disapproved");
        web.moderatorPanelPage().updateRestaurantsStatusInDataBase();
    }
}
