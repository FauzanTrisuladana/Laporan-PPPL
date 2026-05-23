package locators;

import org.openqa.selenium.By;

public final class CentralizedLocators {
    private CentralizedLocators() {
        // utility class
    }

    public static final class SauceDemo {
        private SauceDemo() {
        }

        public static final By USERNAME_INPUT = By.id("user-name");
        public static final By PASSWORD_INPUT = By.id("password");
        public static final By LOGIN_BUTTON = By.id("login-button");
        public static final By INVENTORY_PRODUCTS_LABEL = By.xpath("//*[contains(text(), 'Products')]");
    }

    public static final class Bing {
        private Bing() {
        }

        public static final By SEARCH_INPUT = By.id("sb_form_q");
        public static final By SEARCH_FORM = By.id("sb_form");
    }

    public static final class PracticeTestAutomation {
        private PracticeTestAutomation() {
        }

        public static final By JAVA_FILTER = By.xpath("//label[contains(., 'Java')]/input");
        public static final By BEGINNER_FILTER = By.xpath("//label[contains(., 'Beginner')]/input");
        public static final By INTERMEDIATE_FILTER = By.xpath("//label[contains(., 'Intermediate')]/input");
        public static final By ADVANCED_FILTER = By.xpath("//label[contains(., 'Advanced')]/input");
        public static final By VISIBLE_COURSE_ROWS = By.xpath("//table[@id='courses_table']//tbody/tr[@style!='display: none;']");
        public static final By JAVA_FOR_TESTERS_VIEW_LINK = By.xpath(
                "//table[@id='courses_table']//tbody/tr[td[contains(., 'Java for Testers')]]//a[text()='View']"
        );
    }

    public static final class TheInternet {
        private TheInternet() {
        }

        public static final By HOVER_FIGURES = By.cssSelector(".figure");
        public static final By HOVER_CAPTION = By.cssSelector(".figcaption h5");

        public static final By KEY_PRESSES_TARGET = By.id("target");
        public static final By KEY_PRESSES_RESULT = By.id("result");

        public static final By DRAG_AND_DROP_BOX_A = By.id("column-a");
        public static final By DRAG_AND_DROP_BOX_B = By.id("column-b");
        public static final By DRAG_AND_DROP_BOX_HEADER = By.cssSelector("header");
    }
}
