package page;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularPartialButtonText;
import com.paulhammant.ngwebdriver.NgWebDriver;
import model.InstancesForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import servise.InstancesFormCreator;
import util.DataTypeConverter;

public class PricingCalculatorPageComputeEnginePopup extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private NgWebDriver ngDriver;

    @FindBy(id = "compute")
    private WebElement popupComputeEngine;

    @FindBy(xpath = "//div[contains(text(), 'VM class')]")
    private WebElement vMclass;

    @FindBy(xpath = "//div[contains(text(), 'Instance type')]")
    private WebElement instanceType;

    @FindBy(xpath = "//div[contains(text(), 'Region')]")
    private WebElement region;

    @FindBy(xpath = "//div[contains(text(), 'Commitment term')]")
    private WebElement commitmentTerm;

    @FindBy(xpath = "//b[contains(text(), 'Total Estimated Cost')]")
    private WebElement totalEstimatedCostPerMonth;

    @ByAngularPartialButtonText.FindBy(partialButtonText = "Email Estimate")
    private WebElement buttonEmailEstimate;

    public PricingCalculatorPageComputeEnginePopup(WebDriver driver) {
        super(driver);
        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
    }

    public double getTotalEstimatedCostPerMonth() {
        wait.until(ExpectedConditions.visibilityOf(popupComputeEngine));
        String stringTotalEstimatedCostPerMonth = totalEstimatedCostPerMonth.getText();
        return DataTypeConverter.stringToDouble(stringTotalEstimatedCostPerMonth);
    }

    public EmailYourEstimatePopup openEmailYourEstimatePopup() {
        wait.until((ExpectedConditions.visibilityOfAllElementsLocatedBy(ByAngular.partialButtonText("Email Estimate"))));
        executor.executeScript("arguments[0].click();", buttonEmailEstimate);
        logger.info("Opened 'Email Your Estimate' Popup Form");
        return new EmailYourEstimatePopup(driver);
    }

    public InstancesForm getEstimateComputeEnginePopupData() {
        return InstancesFormCreator.estimateComputeEnginePopupData(vMclass.getText(),
                instanceType.getText(),
                region.getText(),
                commitmentTerm.getText());
    }

}
