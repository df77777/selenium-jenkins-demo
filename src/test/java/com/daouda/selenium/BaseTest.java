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
        String browser = System.getProperty("BROWSER", "chrome"); // valeur par défaut
        URL gridUrl = new URL("http://localhost:4444/wd/hub");

        switch (browser.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                //firefoxOptions.addArguments("--headless=new");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                firefoxOptions.addArguments("--window-size=1366,768");
                driver = new RemoteWebDriver(gridUrl, firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--headless=new");
                edgeOptions.addArguments("--disable-gpu");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                edgeOptions.addArguments("--window-size=1366,768");
                driver = new RemoteWebDriver(gridUrl, edgeOptions);
                break;

            default: // Chrome
                ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.addArguments("--headless=new");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--window-size=1366,768");
                driver = new RemoteWebDriver(gridUrl, chromeOptions);
                break;
        }
    }
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

