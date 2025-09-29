package com.daouda.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class AppTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver",
                "C:\\Users\\I767569\\IdeaProjects\\java_essentials\\Selenium\\msedgedriver.exe");

        EdgeOptions options = new EdgeOptions();

        // ✅ Flags spécifiques pour Jenkins / environnements headless
        options.addArguments("--headless");           // Headless stable
        options.addArguments("--disable-gpu");            // Evite les crashs GPU
        options.addArguments("--no-sandbox");             // Important en CI/CD
        options.addArguments("--disable-dev-shm-usage");  // Evite problème mémoire
        options.addArguments("--remote-allow-origins=*"); // Permet connexions


        // ✅ Certains Jenkins Windows/Linux nécessitent ceci :
        options.setCapability("ms:edgeOptions", options);

        driver = new EdgeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        actions = new Actions(driver);
    }



    @Test
    public void testAllAutomation() throws InterruptedException, IOException {
        driver.get("https://testautomationpractice.blogspot.com/?utm_source=chatgpt.com");
        System.out.println("Le but est d'automatiser le maximum d'élément possible sur : " + driver.getTitle());

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
        /*

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

        Thread.sleep(1000);
        // Alertes
        Alert alert1 = driver.switchTo().alert();
        Thread.sleep(1000);
        System.out.println("Alert text: " + alert1.getText());
        Thread.sleep(1000);
        alert1.accept();
        Thread.sleep(1000);

        Alert alert2 = driver.switchTo().alert();
        Thread.sleep(1000);
        System.out.println("Confirm text: " + alert2.getText());
        Thread.sleep(1000);
        alert2.dismiss();
        Thread.sleep(1000);

        Alert alert3 = driver.switchTo().alert();
        Thread.sleep(1000);
        System.out.println("Prompt text: " + alert3.getText());
        Thread.sleep(1000);
        alert3.sendKeys("test");
        Thread.sleep(1000);
        alert3.accept();
        Thread.sleep(1000);

        // Fenêtres popup
        String mainWindow = driver.getWindowHandle();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@onclick='myFunction()']")).click();
        Thread.sleep(1000);
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                driver.switchTo().window(win);
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);

        driver.findElement(By.id("PopUp")).click();
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(mainWindow)) {
                driver.switchTo().window(win);
                driver.close();
            }
        }

        // Hover
        try {
            WebElement hover = driver.findElement(By.xpath("//button[text()='Point Me']"));
            actions.scrollToElement(hover).moveToElement(hover).perform();
            driver.findElement(By.linkText("Mobiles")).click();
            System.out.println("Hover OK");
        } catch (Exception e) {
            System.out.println("Hover NOT OK");
        }

        // Double click
        try {
            WebElement doubleclick = driver.findElement(By.xpath("//button[@ondblclick='myFunction1()']"));
            actions.doubleClick(doubleclick).perform();
            System.out.println("Double clique OK");
        } catch (Exception e) {
            System.out.println("Double clique non OK");
        }

         */
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
