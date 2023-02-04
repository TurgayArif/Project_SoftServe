package com.easyrestaurant.core;

import com.easyrestaurant.pages.*;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Web {
    protected Logger log;
    private WebDriver driver;
    private HomePage homePage;
    private RestaurantsPage restaurantsPage;
    private SignInPage signInPage;
    private ModeratorPanelPage moderatorPanelPage;
    private WaiterPanelPage waiterPanelPage;



    public Web(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    public HomePage homePage() {

        if (this.homePage == null) {
            homePage = new HomePage(driver, log);
            PageFactory.initElements(driver, homePage);
        }
        return homePage;
    }

    public RestaurantsPage restaurantsPage() {
        if (this.restaurantsPage == null) {
            restaurantsPage = new RestaurantsPage(driver, log);
            PageFactory.initElements(driver, restaurantsPage);
        }
        return restaurantsPage;
    }

    public SignInPage signInPage() {
        if (this.signInPage == null) {
            signInPage = new SignInPage(driver, log);
            PageFactory.initElements(driver, signInPage);
        }
        return signInPage;
    }

    public ModeratorPanelPage moderatorPanelPage() {
        if (this.moderatorPanelPage == null) {
            moderatorPanelPage = new ModeratorPanelPage(driver, log);
            PageFactory.initElements(driver, moderatorPanelPage);
        }
        return moderatorPanelPage;
    }

    public WaiterPanelPage waiterPanelPage() {
        if (this.waiterPanelPage == null) {
            waiterPanelPage = new WaiterPanelPage(driver, log);
            PageFactory.initElements(driver, waiterPanelPage);
        }
        return waiterPanelPage;
    }
}
