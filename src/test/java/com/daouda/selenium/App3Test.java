package com.daouda.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class App3Test extends BaseTest{
    private WebDriver driver;

    @Test
    public void testFormulaireSeleniumPractice() throws InterruptedException {
        driver.get("https://selenium-practice.netlify.app/?utm_source=chatgpt.com");
        driver.manage().window().setSize(new Dimension(1366,768));
        System.out.println("Début AppTest3");
        Thread.sleep(1000);

        // Saisie du nom
        WebElement nameField = driver.findElement(By.cssSelector("input[type=text]"));
        nameField.sendKeys("Daouda");
        Thread.sleep(1000);

        // Sélection dans la liste déroulante
        Select listeDeroulante = new Select(driver.findElement(By.xpath("//select[@name='selection']")));
        listeDeroulante.selectByVisibleText("Item 2");
        Thread.sleep(1000);

        // Case à cocher
        WebElement choix3 = driver.findElement(By.cssSelector("input[name='check3']"));
        if (!choix3.isSelected()) {
            choix3.click();
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 100);");
            Thread.sleep(1000);
            assertTrue("La case 3 devrait être cochée après le clic", choix3.isSelected());
        } else {
            choix3.clear();
            assertFalse("La case 3 devrait être décochée après clear()", choix3.isSelected());
        }

        // Champ de date
        WebElement dateField = driver.findElement(By.xpath("//input[@name='date']"));
        dateField.sendKeys("29-07-2025");
        Thread.sleep(1000);

        // Soumission du formulaire
        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
        submitButton.click();
        Thread.sleep(1000);

        // Retour en haut de la page
        WebElement topPage = driver.findElement(By.className("practice-form"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topPage);
        Thread.sleep(500);
    }

    @After
    public void tearDown() {
        System.out.println("Fin du Test 3");
        if (driver != null) {
            driver.quit();
        }
    }
}
