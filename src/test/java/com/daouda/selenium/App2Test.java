package com.daouda.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;

public class App2Test {

    private WebDriver driver;

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

    }

    @Test
    public void testFormulaireTechlistic() throws InterruptedException {
        // Initialisation de WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
        driver.manage().window().setSize(new Dimension(1366,768));
        Thread.sleep(1000);
        System.out.println("AppTest2");

        try {
            // Cookies
            Thread.sleep(3000);
            WebElement cookies = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='ez-accept-all']")));
            cookies.click();
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println("No Such Cookies");
        }

/*
        // Scroll vers le formulaire
        WebElement formulaire = driver.findElement(By.xpath("//span[@style='font-size:large']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", formulaire);
        Thread.sleep(3000);

 */

        // Remplissage du formulaire
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys("Daouda");
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys("Fofana");
        Thread.sleep(3000);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy({ top: 100, behavior: 'smooth'});");
        Thread.sleep(1000);

        driver.findElement(By.id("sex-0")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("exp-1")).click();
        Thread.sleep(3000);
        driver.findElement(By.id("datepicker")).sendKeys("29/07/2025");
        Thread.sleep(3000);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy({ top: 100, behavior: 'smooth'});");
        Thread.sleep(1000);

        // Clic sur le bouton
        try {
            WebElement bouton = driver.findElement(By.xpath("//button[@class='btn btn-info']"));
            bouton.click();
            System.out.println("Le bouton a été cliqué");
            System.out.println("Le test est terminé, Fermeture de la page dans 5 secondes");
        } catch (Exception e) {
            System.out.println("Erreur lors du clic sur le bouton : " + e.getMessage());
        }

        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        System.out.println("Fin du Test 2");
        if (driver != null) driver.quit();
    }
}
