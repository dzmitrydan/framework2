package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Dropdown {

    public static void selecItemByText(String dropdownItemText, WebDriver driver, JavascriptExecutor executor) {

        if (!dropdownItemText.isEmpty()){
            WebElement dropdownItem = driver.findElement(By.xpath("//div[contains(text(), '" + dropdownItemText +"')]"));
            executor.executeScript("arguments[0].click();", dropdownItem);
            executor.executeScript("arguments[0].click();", dropdownItem);

            Logger logger = LogManager.getRootLogger();
            logger.info("Dropdown item with text='" + dropdownItem.getAttribute("innerHTML").trim() +"' selected");
        }
    }


    public static void selecItemByElement(WebElement dropdownItem, JavascriptExecutor executor) {

        executor.executeScript("arguments[0].click();", dropdownItem);
        executor.executeScript("arguments[0].click();", dropdownItem);

        Logger logger = LogManager.getRootLogger();
        logger.info("Dropdown item with text='" + dropdownItem.getAttribute("innerHTML").trim() +"' selected");
    }


}
