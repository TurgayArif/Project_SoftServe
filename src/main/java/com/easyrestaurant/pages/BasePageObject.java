package com.easyrestaurant.pages;

import com.easyrestaurant.core.PSQL;
import com.easyrestaurant.utils.Defaults;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePageObject extends Defaults {
    protected WebDriver driver;
    protected Logger log;
    protected PSQL psql;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        this.psql = new PSQL();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    protected void click(WebElement webElement) {
        waitForVisibilityOfElement(webElement);
        webElement.click();
    }

    protected void type(WebElement webElement, String text) {
        waitForVisibilityOfElement(webElement);
        webElement.sendKeys(text);
    }

    protected WebElement find(WebElement webElement) {
        waitForVisibilityOfElement(webElement);
        return webElement;
    }

    protected List<WebElement> findAll(By locator) {
        waitForPresenceOfElement(locator);
        return driver.findElements(locator);
    }

    protected String getText(WebElement webElement) {
        waitForVisibilityOfElement(webElement);
        return webElement.getText();
    }

    protected void waitForVisibilityOfElement(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForVisibilityOfAllElements(List<WebElement> list) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

    protected void waitForPresenceOfElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    protected void waitForUrlToBe(String urlToBe) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains(urlToBe));
    }

}
