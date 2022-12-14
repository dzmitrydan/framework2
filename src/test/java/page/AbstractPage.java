package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected final Logger logger;
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor executor;

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        executor = (JavascriptExecutor) driver;
        logger = LogManager.getRootLogger();
        PageFactory.initElements(driver, this);
    }

    protected void clickWebElement(WebElement webElement) {
        executor.executeScript("arguments[0].click();", webElement);
    }

    protected void checkboxCheck(WebElement checkbox, boolean isChecked) {
        if (isChecked) {
            executor.executeScript("arguments[0].click();", checkbox);
        }
        logger.info("Checkbox is checked = " + checkbox.getAttribute("aria-checked"));
    }

}
