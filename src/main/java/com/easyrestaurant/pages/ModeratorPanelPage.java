package com.easyrestaurant.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.sql.SQLException;
import java.util.List;

public class ModeratorPanelPage extends BasePageObject {

    @FindBy(xpath = "//button[@role='tab']")
    List<WebElement> topNavBarButtonSelector;
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div/div/div/div[1]/div/span[1]")
    List<WebElement> restaurantsNameSelector;
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div/div/div/div[3]/button[2]")
    List<WebElement> approveButtonSelector;
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div/div/div/div[3]/button[1]")
    List<WebElement> disapproveButtonSelector;
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div/div/div/div[1]/div[2]/span[1]")
    List<WebElement> approvedRestaurantsNameSelector;
    @FindBy(xpath = "//*[@id=\"root\"]/div/main/div[2]/div/div/div/div[2]/div[2]/span")
    List<WebElement> restaurantStatusSelector;
    @FindBy(id = "client-snackbar")
    WebElement snackBar;


    public ModeratorPanelPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public ModeratorPanelPage clickOnNavBarButton(String buttonToClick) {
        waitForVisibilityOfAllElements(topNavBarButtonSelector);
        for (WebElement e : topNavBarButtonSelector) {
            if (e.getText().toLowerCase().contains(buttonToClick.toLowerCase())) {
                e.click();
                break;
            }
        }
        return this;
    }

    public ModeratorPanelPage approveRestaurant(String restName) {
        waitForVisibilityOfAllElements(restaurantsNameSelector);
        for (WebElement e : restaurantsNameSelector) {
            if (e.getText().equalsIgnoreCase(restName.toLowerCase())) {
                for (WebElement f : approveButtonSelector)
                    click(f);
                break;
            }
        }
        return this;
    }

    public String getSnackBarText() {
        return getText(snackBar).toLowerCase();
    }

    public ModeratorPanelPage disapproveRestaurant(String restName) {
        waitForVisibilityOfAllElements(disapproveButtonSelector);
        for (WebElement e : restaurantsNameSelector) {
            if (e.getText().toLowerCase().contains(restName.toLowerCase())) {
                for (WebElement f : disapproveButtonSelector) {
                    click(f);
                    break;
                }
            }
        }
        return this;
    }

    public boolean isRestaurantApproved(String restName, String status) {
        waitForVisibilityOfAllElements(approvedRestaurantsNameSelector);
        for (WebElement e : approvedRestaurantsNameSelector) {
            if (e.getText().equalsIgnoreCase(restName.toLowerCase())) {
                waitForVisibilityOfAllElements(restaurantStatusSelector);
                for (WebElement f : restaurantStatusSelector) {
                    if (getText(f).equalsIgnoreCase(status)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public ModeratorPanelPage updateRestaurantsStatusInDataBase() {
        try {
            psql.connectToDb();
            var results = psql.executeQueryAndGetResults("select status from restaurants where id in(1,4);");
            if (results != null) {
                while (results.next()) {
                    int status = results.getInt("status");
                    if (status != 0) {
                        psql.executeUpdate("update restaurants set status = 0 where id in (1,4);");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }
}
