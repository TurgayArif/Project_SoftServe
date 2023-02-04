package com.easyrestaurant.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.easyrestaurant.core.TestUtils;

/**
 * Unit test for simple App.
 */
public class HomePageTests extends TestUtils {

    @Description("Can open home page.")
    @Test(priority = 1)
    public void canOpenHomePage() {
        web.homePage().navigateToHomePage();
        Allure.step("Opened home page.");

        var currUrl = web.homePage().getCurrentURL();
        Assert.assertEquals(currUrl, "http://localhost:3000/", "The current URL is not equal to expected!");
        Allure.step("Verifying the current url.");

    }
}
