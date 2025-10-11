package com.daouda.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @Before
    public void setUp() throws MalformedURLException {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage"); // très important pour éviter les crashs dans Docker
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-background-networking");
        options.addArguments("--disable-software-rasterizer");


        // URL du hub Selenium Grid
        URL gridUrl = new URL("http://localhost:4444/wd/hub");

        driver = new RemoteWebDriver(gridUrl, options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        actions = new Actions(driver);
    }



    @Test
    public void testAllAutomation() throws InterruptedException, IOException {
        driver.get("https://testautomationpractice.blogspot.com/?utm_source=chatgpt.com");
        driver.manage().window().setSize(new Dimension(1366,768));
        Thread.sleep(1000);
        System.out.println("AppTest1");

        // Cookie
        wait.until(ExpectedConditions.elementToBeClickable(By.id("cookieChoiceDismiss"))).click();
        Thread.sleep(1000);

        // Formulaire
        driver.findElement(By.id("name")).sendKeys("Alice");
        Thread.sleep(1000);
        driver.findElement(By.id("email")).sendKeys("alice@icloud.com");
        Thread.sleep(1000);
        driver.findElement(By.id("phone")).sendKeys("0789098716");
        Thread.sleep(1000);
        driver.findElement(By.id("textarea")).sendKeys("16 rue de Karl 01267");
        Thread.sleep(1000);
        driver.findElement(By.id("female")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("sunday")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("monday")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("tuesday")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("wednesday")).click();
        Thread.sleep(1000);

        Select day = new Select(driver.findElement(By.id("country")));
        day.selectByVisibleText("France");
        Thread.sleep(1000);

        Select couleur = new Select(driver.findElement(By.id("colors")));
        couleur.selectByVisibleText("White");
        Thread.sleep(1000);

        Select animals = new Select(driver.findElement(By.id("animals")));
        animals.selectByValue("zebra");
        Thread.sleep(1000);

        driver.findElement(By.id("datepicker")).sendKeys("10/25/2025");
        Thread.sleep(1000);

        // Date readonly
        WebElement date2 = driver.findElement(By.id("txtDate"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly')", date2);
        date2.sendKeys("10/25/2025");
        Thread.sleep(1000);

        driver.findElement(By.id("start-date")).sendKeys("01/01/1999");
        driver.findElement(By.id("end-date")).sendKeys("25/12/2000");
        driver.findElement(By.xpath("//button[@onclick='calculateRange()']")).click();
        Thread.sleep(1000);


        // Wikipedia search
        driver.findElement(By.id("Wikipedia1_wikipedia-search-input")).sendKeys("chatgpt");
        driver.findElement(By.xpath("//input[@class='wikipedia-search-button']")).click();
        Thread.sleep(1000);


        // Dynamic button start/stop
        try {
            WebElement startButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("start"))));
            startButton.click();
            Thread.sleep(1000);
            WebElement stopButton = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name("stop"))));
            stopButton.click();
            System.out.println("Start/Stop OK");
        } catch (NoSuchElementException e) {
            System.out.println("Start/Stop non présent");
        }

    }

    @After
    public void tearDown() throws InterruptedException {
        System.out.println("Fin du Test 1");
        if (driver != null) driver.quit();

    }
}
