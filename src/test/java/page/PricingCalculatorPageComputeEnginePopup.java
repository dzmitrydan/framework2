package page;

import com.paulhammant.ngwebdriver.NgWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.StringExtractor;

public class PricingCalculatorPageComputeEnginePopup extends AbstractPage {

    private NgWebDriver ngDriver;

    @FindBy(id = "compute")
    private WebElement popupComputeEngine;;

    @FindBy(xpath = "//b[contains(text(), 'Total Estimated Cost')]")
    private WebElement totalEstimatedCostPerMonth;

    @FindBy(id = "Email Estimate")
    private WebElement buttonEmailEstimate;

    public PricingCalculatorPageComputeEnginePopup(WebDriver driver) {
        super(driver);
        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
    }

    public double getTotalEstimatedCostPerMonth() {
        wait.until(ExpectedConditions.visibilityOf(popupComputeEngine));
        String stringTotalEstimatedCostPerMonth = totalEstimatedCostPerMonth.getText();
        return StringExtractor.stringToDouble(stringTotalEstimatedCostPerMonth);
    }

    public EmailYourEstimatePopup openEmailYourEstimatePopup() {
        wait.until(ExpectedConditions.elementToBeClickable(buttonEmailEstimate));
        clickWebElement(buttonEmailEstimate);
        logger.info("Opened 'Email Your Estimate' Popup Form");
        return new EmailYourEstimatePopup(driver);
    }
}
