package test;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import locators.CentralizedLocators;

public class XpathTest {
    WebDriver driver;

    @BeforeEach
    public void setup() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        this.driver.get("https://practicetestautomation.com/practice-test-table/");
    }

    @Test
    public void filterJavaCourses() {
        WebElement javaFilter = this.driver.findElement(CentralizedLocators.PracticeTestAutomation.JAVA_FILTER);
        javaFilter.click();
        
        List<WebElement> tableRows = this.driver.findElements(
            CentralizedLocators.PracticeTestAutomation.VISIBLE_COURSE_ROWS
        );
        
        // Verify each course row contains 'Java' text using XPath with contains and text()
        for (WebElement row : tableRows) {
            String courseText = row.getText();
            assertTrue(courseText.contains("Java"));
        }
    }

    @Test
    public void beginnerOnly() {
        WebElement beginnerFilter = this.driver.findElement(CentralizedLocators.PracticeTestAutomation.BEGINNER_FILTER);
        if (!beginnerFilter.isSelected()) {
            beginnerFilter.click();
        }

        WebElement intermediateFilter = this.driver.findElement(CentralizedLocators.PracticeTestAutomation.INTERMEDIATE_FILTER);
        if (intermediateFilter.isSelected()) {
            intermediateFilter.click();
        }

        WebElement advancedFilter = this.driver.findElement(CentralizedLocators.PracticeTestAutomation.ADVANCED_FILTER);
        if (advancedFilter.isSelected()) {
            advancedFilter.click();
        }
        
        List<WebElement> tableRows = this.driver.findElements(
            CentralizedLocators.PracticeTestAutomation.VISIBLE_COURSE_ROWS
        );
        
        for (WebElement row : tableRows) {
            String courseText = row.getText();
            assertTrue(courseText.contains("Beginner"));
        }
    }

    @Test
    public void accessLinkViewFromJavaForTester() {
        String originalWindow = this.driver.getWindowHandle();

        WebElement javaForTesterLink = this.driver.findElement(
            CentralizedLocators.PracticeTestAutomation.JAVA_FOR_TESTERS_VIEW_LINK
        );
        javaForTesterLink.click();

        for (String windowHandle : this.driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                this.driver.switchTo().window(windowHandle);
                break;
            }
        }

        String url = this.driver.getCurrentUrl();
        assertTrue(url.contains("udemy.com/course/java-for-testers-dmitry"));
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit();
    }
}
