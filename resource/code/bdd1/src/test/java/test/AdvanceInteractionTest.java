package test;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class AdvanceInteractionTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
    }

    @Test
    public void hoverTest() {
        this.driver.get("https://the-internet.herokuapp.com/hovers");

        List<WebElement> figures = driver.findElements(By.cssSelector(".figure"));

        Actions actions = new Actions(driver);
        String[] expected = {"name: user1", "name: user2", "name: user3"};

        for (int i = 0; i < Math.min(3, figures.size()); i++) {
            WebElement fig = figures.get(i);
            actions.moveToElement(fig).perform();
            WebElement caption = fig.findElement(By.cssSelector(".figcaption h5"));
            String text = caption.getText().trim();
            Assertions.assertEquals(expected[i], text);
        }
    }

    @Test
    public void keyPressesTest() {
        this.driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement input = driver.findElement(By.id("target"));

        Actions actions = new Actions(driver);
        // focus with mouse then send keys one-by-one and assert each result
        actions.moveToElement(input).click().perform();

        actions.sendKeys(Keys.SHIFT).perform();
        String resText = driver.findElement(By.id("result")).getText().trim();
        Assertions.assertTrue(resText.contains("SHIFT"));

        actions.sendKeys(Keys.UP).perform();
        resText = driver.findElement(By.id("result")).getText().trim();
        Assertions.assertTrue(resText.contains("UP"));

        actions.sendKeys(Keys.BACK_SPACE).perform();
        resText = driver.findElement(By.id("result")).getText().trim();
        Assertions.assertTrue(resText.contains("BACK_SPACE"));
    }

    @Test
    public void dragAndDropTest() {
        this.driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement boxA = driver.findElement(By.id("column-a"));
        WebElement boxB = driver.findElement(By.id("column-b"));

        Actions actions = new Actions(driver);
        // simulate mouse drag-and-drop: click-and-hold, move, release
        actions.clickAndHold(boxA).pause(Duration.ofMillis(200)).moveToElement(boxB).pause(Duration.ofMillis(200)).release().perform();

        String headerText = boxB.findElement(By.cssSelector("header")).getText().trim();
        Assertions.assertEquals("A", headerText);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}
