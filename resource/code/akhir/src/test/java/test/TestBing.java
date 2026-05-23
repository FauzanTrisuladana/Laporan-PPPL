package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import locators.CentralizedLocators;

public class TestBing {
    @Test
    public void searchTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.bing.com/");

        WebElement seacrh = driver.findElement(CentralizedLocators.Bing.SEARCH_INPUT);
        seacrh.sendKeys("selenium");
        WebElement form = driver.findElement(CentralizedLocators.Bing.SEARCH_FORM);
        form.submit();

        //Assert
        String title = driver.getTitle();
        String expected = "selenium - Search";

        assertEquals(title, expected);
        

        driver.quit();
    }
}
