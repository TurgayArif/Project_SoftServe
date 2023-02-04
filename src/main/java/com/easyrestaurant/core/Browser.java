package com.easyrestaurant.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Browser {

    private WebDriver driver;
    private Logger log;

    public Browser(Logger log) {
        this.log = log;
    }

    public static void downloadBrowser() {
        WebDriverManager.chromedriver().setup();
    }

    public WebDriver startBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public void tearDown() {
        driver.quit();
    }

    protected WebDriver getDriver() {
        return this.driver;
    }
}
