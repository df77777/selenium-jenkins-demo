package com.daouda.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.URL;
import java.net.MalformedURLException;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        String browser = System.getProperty("BROWSER", "chrome"); // par défaut
        URL gridUrl = new URL("http://localhost:4444/wd/hub");

        switch (browser.toLowerCase()) {
            case "firefox":
                driver = new RemoteWebDriver(gridUrl, new FirefoxOptions());
                break;
            case "edge":
                driver = new RemoteWebDriver(gridUrl, new EdgeOptions());
                break;
            default:
                driver = new RemoteWebDriver(gridUrl, new ChromeOptions());
                break;
        }

        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
