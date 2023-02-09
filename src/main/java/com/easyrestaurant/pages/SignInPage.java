package com.easyrestaurant.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePageObject {

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailInputSelector;
    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordInputSelector;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButtonSelector;
    @FindBy(xpath = "//p[contains(@class, 'FormHelperText-error')]")
    WebElement errorMessage;
    @FindBy(id = "client-snackbar")
    WebElement snackBar;


    public SignInPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public SignInPage signIn(String username, String password) {
        type(emailInputSelector, username);
        type(passwordInputSelector, password);
        click(submitButtonSelector);
        return this;
    }

    public SignInPage signIn(String username, String password, String urlToBe) {
        type(emailInputSelector, username);
        type(passwordInputSelector, password);
        click(submitButtonSelector);
        waitForUrlToBe(urlToBe);
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getSnackBarErrorMessage() {
        return getText(snackBar);
    }
}
