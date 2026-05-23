package test;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import locators.CentralizedLocators;

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

        List<WebElement> figures = driver.findElements(CentralizedLocators.TheInternet.HOVER_FIGURES);

        Actions actions = new Actions(driver);
        String[] expected = {"name: user1", "name: user2", "name: user3"};

        for (int i = 0; i < Math.min(3, figures.size()); i++) {
            WebElement fig = figures.get(i);
            actions.moveToElement(fig).perform();
            WebElement caption = fig.findElement(CentralizedLocators.TheInternet.HOVER_CAPTION);
            String text = caption.getText().trim();
            Assertions.assertEquals(expected[i], text);
        }
    }

    @Test
    public void keyPressesTest() {
        this.driver.get("https://the-internet.herokuapp.com/key_presses");

        WebElement input = driver.findElement(CentralizedLocators.TheInternet.KEY_PRESSES_TARGET);

        Actions actions = new Actions(driver);
        // focus with mouse then send keys one-by-one and assert each result
        actions.moveToElement(input).click().perform();

        actions.sendKeys(Keys.SHIFT).perform();
        String resText = driver.findElement(CentralizedLocators.TheInternet.KEY_PRESSES_RESULT).getText().trim();
        Assertions.assertTrue(resText.contains("SHIFT"));

        actions.sendKeys(Keys.UP).perform();
        resText = driver.findElement(CentralizedLocators.TheInternet.KEY_PRESSES_RESULT).getText().trim();
        Assertions.assertTrue(resText.contains("UP"));

        actions.sendKeys(Keys.BACK_SPACE).perform();
        resText = driver.findElement(CentralizedLocators.TheInternet.KEY_PRESSES_RESULT).getText().trim();
        Assertions.assertTrue(resText.contains("BACK_SPACE"));
    }

    @Test
    public void dragAndDropTest() {
        this.driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement boxA = driver.findElement(CentralizedLocators.TheInternet.DRAG_AND_DROP_BOX_A);
        WebElement boxB = driver.findElement(CentralizedLocators.TheInternet.DRAG_AND_DROP_BOX_B);

        Actions actions = new Actions(driver);
        // simulate mouse drag-and-drop: click-and-hold, move, release
        actions.clickAndHold(boxA).pause(Duration.ofMillis(200)).moveToElement(boxB).pause(Duration.ofMillis(200)).release().perform();

        String headerText = boxB.findElement(CentralizedLocators.TheInternet.DRAG_AND_DROP_BOX_HEADER).getText().trim();
        Assertions.assertEquals("A", headerText);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}
