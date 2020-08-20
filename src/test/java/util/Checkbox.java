package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Checkbox {

    public static void check(WebElement checkbox, boolean isChecked, JavascriptExecutor executor) {

        if (isChecked) {
            executor.executeScript("arguments[0].click();", checkbox);
        }

        Logger logger = LogManager.getRootLogger();
        logger.info("Checkbox is checked = " + checkbox.getAttribute("aria-checked"));
    }
}
