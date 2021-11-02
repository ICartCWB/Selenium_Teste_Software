
package com.mycompany.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;

/**
 *
 * @author:
 * Daniel Costa – TIA 3199116-5
 * Estevan Fonseca – TIA 4192529-7
 * Israel Nunes da Silva – TIA 3198368-5

 */


public class MusescoresearchTest {
    
    private WebDriver driver;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() {
        String add=System.getProperty("user.dir")+"\\geckodriver.exe";
        System.setProperty("webdriver.gecko.driver",add);
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
    }
    
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void ExecucaoMusicaFreeValida() {
        // Array de nomes de músicas válidas para testar execução
        String[] nomesMusica = {"Revolutionary", "Wave", "Mario Bros Theme"};
        for (String nome : nomesMusica) {
            driver.get("https://musescore.com/");
            driver.findElement(By.name("value")).sendKeys(nome);
            driver.findElement(By.xpath("//button[@type=\'submit\']")).click();
            driver.findElement(By.xpath("//a/h2")).click();
            driver.findElement(By.cssSelector(".\\_3aSWK > .\\_36xBp > path")).click();
            try { Thread.sleep (6000); } catch (InterruptedException ex) {}
            Boolean musicStoped = (Boolean) js.executeScript("return document.querySelector('audio').paused;");

            assertEquals(false,musicStoped);
        }
    }
    
    @Test
    public void MusicaOfficialValida() {
        // Array de nomes de músicas oficiais
        String[] nomesMusica = {"Avengers","James Bond"};
        for (String nome : nomesMusica) {
            driver.get("https://musescore.com/");
            driver.findElement(By.name("value")).sendKeys(nome);
            driver.findElement(By.xpath("//button[@type=\'submit\']")).click();
            String sResult = driver.findElement(By.xpath("/html/body/div[1]/div/section/section/main/div[2]/section/article[1]/div/div[1]/div[2]/div/a")).getText();
            assertEquals(" Official",sResult);
        }
    }
    
    @Test
    public void MusicaInvalida() {
        // Array de nomes de músicas inválidas
        String[] nomesMusica = {"wqefqwefwqfrfqewrfqer", "0987654321", "(*%*#%", "dsfsd3411243","!@#!@45345"};
        for (String nome : nomesMusica) {
            driver.get("https://musescore.com/");
            driver.findElement(By.name("value")).sendKeys(nome);
            driver.findElement(By.xpath("//button[@type=\'submit\']")).click();
            String sResult = driver.findElement(By.xpath("/html/body/div[1]/div/section/section/main/div[2]")).getText();

            assertEquals("Please, check the spelling and search filters or shorten the query.",sResult);
        }
        
    }

    private void showMessageDialog(String property) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
