package com.easyrestaurant.core;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
public class BaseTest {
    protected Browser browser;
    protected Web web;
    protected PSQL psql;
    protected Logger log;
    protected String testName;
    protected String testMethodName;

    @BeforeTest
    public static void beforeTest() {
        Browser.downloadBrowser();
    }

    @BeforeMethod
    public void beforeMethod(ITestContext ctx, Method method) {
        browser = new Browser(log);
        web = new Web(browser.startBrowser(), log);
        this.log = LogManager.getLogger(TestListener.class);
        this.testName = ctx.getCurrentXmlTest().getName();
        this.testMethodName = method.getName();
        Allure.step("Starting browser.");

        psql = new PSQL();
        psql.connectToDb();
    }

    @AfterMethod
    public void afterMethod() {
        Allure.step("Closing browser.");
        browser.tearDown();
        psql.tearDown();
    }

}
