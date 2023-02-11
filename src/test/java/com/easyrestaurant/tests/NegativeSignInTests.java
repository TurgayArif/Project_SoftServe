package com.easyrestaurant.tests;

import com.easyrestaurant.core.CsvDataProvider;
import com.easyrestaurant.core.TestUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class NegativeSignInTests extends TestUtils {

    @Description("Negative log in tests with data provider from csv file.")
    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProvider.class)
    public void negativeSignInTest(Map<String, String> testData) {

        String username = testData.get("username");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        web.homePage().navigateToHomePage();
        Allure.step("Navigate to home page.");
        web.homePage().clickOnSignIn();
        Allure.step("Click on sign in.");
        if (description.equalsIgnoreCase("invalid password")) {
            web.signInPage().signIn(username, password);
            Allure.step("Entering username " + username);
            Allure.step("Entering password " + password);
            var actualErrorMessage = web.signInPage().getSnackBarErrorMessage();
            Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
            Allure.step("Verifying that the actual message contains expected message.");
        } else {
            web.signInPage().signIn(username, password);
            Allure.step("Entering username " + username);
            Allure.step("Entering password " + password);
            var actualError = web.signInPage().getErrorMessage();
            Assert.assertTrue(actualError.contains(expectedErrorMessage));
            Allure.step("Verifying that the actual message contains expected message.");
        }
    }
}
