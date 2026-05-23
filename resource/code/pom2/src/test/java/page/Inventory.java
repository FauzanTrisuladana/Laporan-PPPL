package page;

import org.openqa.selenium.WebDriver;

import locators.CentralizedLocators;

public class Inventory {
    private final WebDriver driver;

    public Inventory(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isDisplayed() {
        return this.driver.findElement(CentralizedLocators.SauceDemo.INVENTORY_PRODUCTS_LABEL).isDisplayed();
    }
}
