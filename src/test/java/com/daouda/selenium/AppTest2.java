package com.daouda.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.Select;

public class AppTest2 {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.edge.driver",
                "C:\\Users\\I767569\\IdeaProjects\\java_essentials\\Selenium\\msedgedriver.exe");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new"); // headless pour Jenkins
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
    }

    @Test
    public void testFormulaireTechlistic() throws InterruptedException {
        driver.get("https://www.techlistic.com/p/selenium-practice-form.html");
        System.out.println("AppTest1");

        // Cookies
        Thread.sleep(3000);
        WebElement cookies = driver.findElement(By.id("ez-accept-all"));
        cookies.click();
        Thread.sleep(3000);

        // Scroll vers le formulaire
        WebElement formulaire = driver.findElement(By.xpath("//span[@style='font-size:large']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", formulaire);
        Thread.sleep(3000);

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

        driver.findElement(By.xpath("//input[@value='Automation Tester']")).click();
        Thread.sleep(3000);
        driver.findElement(By.cssSelector("input[value='Selenium Webdriver']")).click();
        Thread.sleep(3000);

        Select continent = new Select(driver.findElement(By.id("continents")));
        continent.selectByVisibleText("Europe");
        Thread.sleep(3000);

        Select commands = new Select(driver.findElement(By.id("selenium_commands")));
        commands.selectByVisibleText("Navigation Commands");
        Thread.sleep(3000);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 250);");
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
        if (driver != null) driver.quit();
    }
}
