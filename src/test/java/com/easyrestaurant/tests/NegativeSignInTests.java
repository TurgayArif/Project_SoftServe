package com.easyrestaurant.tests;

import com.easyrestaurant.core.CsvDataProvider;
import com.easyrestaurant.core.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class NegativeSignInTests extends TestUtils {

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProvider.class)
    public void negativeSignInTest(Map<String, String> testData) {

        String username = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        web.homePage().navigateToHomePage();
        web.homePage().clickOnSignIn();
        if (description.equalsIgnoreCase("invalid password")) {
            web.signInPage().signIn(username, password);
            var actualErrorMessage = web.signInPage().getSnackBarErrorMessage();
            Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
        } else {
            web.signInPage().signIn(username, password);
            var actualError = web.signInPage().getErrorMessage();
            Assert.assertTrue(actualError.contains(expectedErrorMessage));
        }
    }
}
