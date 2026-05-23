import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SauceTest {

    WebDriver driver;
    WebElement username;
    WebElement password;
    WebElement loginButton;

    @BeforeEach
    public void setup() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://www.saucedemo.com/");
        this.username = this.driver.findElement(By.id("user-name"));
        this.password = this.driver.findElement(By.id("password"));
        this.loginButton = this.driver.findElement(By.id("login-button"));
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
        this.username = this.driver.findElement(By.id("user-name"));
        this.password = this.driver.findElement(By.id("password"));
        this.loginButton = this.driver.findElement(By.id("login-button"));

        this.username.sendKeys(usernameValue);
        this.password.sendKeys("secret_sauce");
        this.loginButton.click();

        // assert
        String url = this.driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        assertEquals(expected, url);
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "locked_out_user"
    })
    public void failedLoginNotBecauseWrongPassword(String usernameValue) {
        this.username = this.driver.findElement(By.id("user-name"));
        this.password = this.driver.findElement(By.id("password"));
        this.loginButton = this.driver.findElement(By.id("login-button"));

        this.username.sendKeys(usernameValue);
        this.password.sendKeys("secret_sauce");
        this.loginButton.click();

        // assert
        String url = this.driver.getCurrentUrl();
        String expected = "https://www.saucedemo.com/inventory.html";
        assertNotEquals(expected, url);
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}
