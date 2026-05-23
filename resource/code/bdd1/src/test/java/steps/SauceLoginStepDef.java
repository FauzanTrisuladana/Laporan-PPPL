package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.Inventory;
import page.LoginPage;

public class SauceLoginStepDef {

    private WebDriver driver;

    private final String baseUrl = "https://www.saucedemo.com/";
    private final String expectedInventoryUrl = "https://www.saucedemo.com/inventory.html";

    @Before
    public void beforeScenario() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseUrl);
    }

    @After
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("User is on the SauceDemo login page")
    public void userIsOnTheSauceDemoLoginPage() {
        driver.get(baseUrl);
        assertEquals(baseUrl, driver.getCurrentUrl());
    }

    @When("User logs in with username {string} and password {string}")
    public void userLogsInWithUsernameAndPassword(String username, String password) {
        driver.findElement(By.id("user-name")).clear();
        driver.findElement(By.id("password")).clear();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }

    @Then("User should be redirected to inventory page")
    public void userShouldBeRedirectedToInventoryPage() {
        assertEquals(expectedInventoryUrl, driver.getCurrentUrl());
    }

    @Then("Inventory page should be displayed")
    public void inventoryPageShouldBeDisplayed() {
        Inventory inventory = new Inventory(driver);
        assertTrue(inventory.isDisplayed());
    }

    @Then("User should not be redirected to inventory page")
    public void userShouldNotBeRedirectedToInventoryPage() {
        assertNotEquals(expectedInventoryUrl, driver.getCurrentUrl());
    }
}
