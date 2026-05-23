package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Inventory {
    private final WebDriver driver;
    private final By productLabel = By.xpath("//*[contains(text(), 'Products')]");

    public Inventory(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isDisplayed() {
        return this.driver.findElement(this.productLabel).isDisplayed();
    }
}
