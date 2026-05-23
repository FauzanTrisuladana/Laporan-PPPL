package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBing {
    @Test
    public void searchTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.bing.com/");

        WebElement seacrh = driver.findElement(By.id("sb_form_q"));
        seacrh.sendKeys("selenium");
        WebElement form = driver.findElement(By.id("sb_form"));
        form.submit();

        //Assert
        String title = driver.getTitle();
        String expected = "selenium - Search";

        assertEquals(title, expected);
        

        driver.quit();
    }
}
