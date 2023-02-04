package com.easyrestaurant.pages;

import com.easyrestaurant.core.Web;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class WaiterPanelPage extends BasePageObject {

    @FindBy(xpath = "//a[@href='/waiter/orders/In progress']")
    WebElement inProgressNavBarSelector;
    @FindBy(xpath = "//a[@href='/waiter/orders/History']")
    WebElement historyNavBarSelector;
    @FindBy(css = "button[aria-label='Show more']")
    List<WebElement> allExpandArrowsSelector;
    @FindBy(xpath = "//span[contains(text(), 'Close order')]")
    WebElement closeOrderButtonSelector;
    @FindBy(xpath = "//*[@id=\"root\"]/main/div/div[1]/div/div/div[1]/div[2]/span[2]")
    WebElement orderInfoTextSelector;
    @FindBy(xpath = "//*[@id=\"root\"]/main/div/div/div/div/div[1]/div[2]/span[2]")
    List<WebElement> allClientInfoSelector;

    public WaiterPanelPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    // Click on In progress in navigation bar
    public WaiterPanelPage clickOnInProgressNavBar() {
        click(inProgressNavBarSelector);
        return this;
    }

    // Click on Down arrow to expand the first order
    public WaiterPanelPage clickOnDownArrowToExpandOrder() {
        for (WebElement e : allExpandArrowsSelector) {
            e.click();
            break;
        }
        return this;
    }

    //Click on Close order
    public WaiterPanelPage clickOnCloseOrder() {
        click(closeOrderButtonSelector);
        return this;
    }

    // Click on History in Navigation bar
    public WaiterPanelPage clickOnHistoryNavBar() {
        click(historyNavBarSelector);
        return this;
    }

    // Get client information
    public String getClientInformation() {
        return getText(orderInfoTextSelector);
    }

    public List<String> getAllClientInfoFromHistory() {
        List<String> clInfo = new ArrayList<>();
        for (WebElement e : allClientInfoSelector) {
            var text = e.getText();
            clInfo.add(text);
        }
        return clInfo;
    }

    public WaiterPanelPage updateDatabaseIfNoOrdersInProgress(String waiterEmail) {
        psql.connectToDb();
        var ordersInProgress = psql.executeQueryAndGetResults("select orders.id, orders.status " +
                "from orders " +
                "left join users on orders.waiter_id = users.id " +
                "where users.email = '"+waiterEmail+"' and orders.status = 'In progress';");
        int orderId = 0;
        String orderStatus = null;
        try {
            while (ordersInProgress.next()) {
                orderId = ordersInProgress.getInt("id");
                orderStatus = ordersInProgress.getString("status");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (orderId == 0 && orderStatus == null) {
            var ordersInHistory = psql.executeQueryAndGetResults("select orders.id, orders.status " +
                    "from orders " +
                    "left join users on orders.waiter_id = users.id " +
                    "where users.email = '"+waiterEmail+"' and orders.status = 'History' limit 1;");
            try {
                while (ordersInHistory.next()) {
                    orderId = ordersInHistory.getInt("id");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            psql.executeUpdate("update orders set status = 'In progress' where id = "+orderId+";");
        }
        psql.tearDown();
        return this;
    }
}
