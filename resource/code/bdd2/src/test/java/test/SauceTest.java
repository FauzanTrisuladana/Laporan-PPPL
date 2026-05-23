package test;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.Inventory;
import page.LoginPage;

public class SauceTest {

    WebDriver driver;
    String expectedUrl = "https://www.saucedemo.com/inventory.html";

    @BeforeEach
    public void setup() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://www.saucedemo.com/");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "standard_user", 
        "problem_user", 
        "performance_glitch_user",
        "error_user",
        "visual_user"
    })
    public void successLoginTest(String usernameValue) {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login(usernameValue, "secret_sauce");

        // assert
        assertEquals(this.expectedUrl, this.driver.getCurrentUrl());
        Inventory inventory = new Inventory(this.driver);
        assertTrue(inventory.isDisplayed());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "locked_out_user"
    })
    public void failedLoginNotBecauseWrongPassword(String usernameValue) {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login(usernameValue, "secret_sauce");

        // assert
        assertNotEquals(this.expectedUrl, this.driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}
