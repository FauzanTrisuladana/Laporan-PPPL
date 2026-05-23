package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private WebDriver driver;

    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        this.driver.findElement(usernameInput).sendKeys(username);
        this.driver.findElement(passwordInput).sendKeys(password);
        this.driver.findElement(loginButton).click();
    }
}
