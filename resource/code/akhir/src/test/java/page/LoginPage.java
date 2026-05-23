package page;

import org.openqa.selenium.WebDriver;

import locators.CentralizedLocators;


public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        this.driver.findElement(CentralizedLocators.SauceDemo.USERNAME_INPUT).sendKeys(username);
        this.driver.findElement(CentralizedLocators.SauceDemo.PASSWORD_INPUT).sendKeys(password);
        this.driver.findElement(CentralizedLocators.SauceDemo.LOGIN_BUTTON).click();
    }
}
