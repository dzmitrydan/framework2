package page;

import wait.LoadPageConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DataTypeConverter;

public class HomePage10MinuteMail extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://10minutemail.com";
    private static final int WAIT_TIMEOUT_SECONDS_FOR_MESSAGE = 60;

    private final Logger logger = LogManager.getRootLogger();
    private WebDriverWait waitMessage;
    private String webBrowserTab;

    @FindBy(id = "mail_address")
    private WebElement fieldMailAddress;

    @FindBy(id = "copy_address")
    private WebElement buttonCopyAddress;

    @FindBy(xpath = "//span[text()='Google Cloud Platform Price Estimate']")
    private WebElement messageFromGoogleCloud;

    @FindBy(xpath = "//*[@id='mobilepadding']/td/table/tbody/tr[2]/td[2]/h3")
    private WebElement totalEstimatedMonthlyCost;

    public HomePage10MinuteMail(WebDriver driver) {
        super(driver);
    }

    public HomePage10MinuteMail openHomePage10MinuteMail() {
        driver.get(HOMEPAGE_URL);

        wait.until(LoadPageConditions.jQueryAJAXsCompleted());
        webBrowserTab = driver.getWindowHandle();
        return this;
    }

    public String getMailAddress() {
        wait.until(ExpectedConditions.attributeToBeNotEmpty(fieldMailAddress, "value"));
        String mailAddress = fieldMailAddress.getAttribute("value");
        logger.info("Get mail address: " + mailAddress);
        return mailAddress;
    }

    public double getTotalEstimatedMonthlyCost() {
        waitMessage = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS_FOR_MESSAGE);
        waitMessage.until(ExpectedConditions.visibilityOf(messageFromGoogleCloud)).click();

        logger.info("Email with total cost has been received");

        executor.executeScript("arguments[0].scrollIntoView(true);", totalEstimatedMonthlyCost);

        String stringTotalEstimatedMonthlyCost = totalEstimatedMonthlyCost.getText();
        return DataTypeConverter.stringToDouble(stringTotalEstimatedMonthlyCost);
    }

    public String getWebBrowserTab() {
        return webBrowserTab;
    }

}
