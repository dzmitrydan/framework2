package googlecloud.page;

import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.ByAngularPartialButtonText;
import com.paulhammant.ngwebdriver.NgWebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DataTypeConverter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PricingCalculatorPageComputeEnginePopup extends AbstractPage {

    private NgWebDriver ngDriver;

    private final Logger logger = LogManager.getRootLogger();

    public PricingCalculatorPageComputeEnginePopup(WebDriver driver) {
        super(driver);
        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
    }


    @FindBy(id = "compute")
    private WebElement popupComputeEngine;

    @FindBy(xpath ="//md-list-item[2]")
    private WebElement vMclass;

    @FindBy(xpath ="//md-list-item[3]")
    private WebElement instanceType;

    @FindBy(xpath ="//md-list-item[4]")
    private WebElement region;

    @FindBy(xpath ="//md-list-item[5]")
    private WebElement localSSD;

    @FindBy(xpath ="//md-list-item[6]")
    private WebElement commitmentTerm;

    @FindBy(xpath ="//h2[@class='md-title']/b[@class='ng-binding']")
    private WebElement totalEstimatedCostPerMonth;

    @ByAngularPartialButtonText.FindBy(partialButtonText = "Email Estimate")
    private WebElement buttonEmailEstimate;


    public double getTotalEstimatedCostPerMonth(){
        wait.until(ExpectedConditions.visibilityOf(popupComputeEngine));
        WebElement totalEstimatedCostPerMonth = popupComputeEngine.findElement(By.xpath("following-sibling::h2/b"));

        String stringTotalEstimatedCostPerMonth = totalEstimatedCostPerMonth.getText();
        return DataTypeConverter.stringToDouble(stringTotalEstimatedCostPerMonth);
    }

    public EmailYourEstimatePopup openEmailYourEstimatePopup(){
        wait.until((ExpectedConditions.visibilityOfAllElementsLocatedBy(ByAngular.partialButtonText("Email Estimate"))));
        executor.executeScript("arguments[0].click();", buttonEmailEstimate);

        logger.info("Opened 'Email Your Estimate' Popup Form");

        return new EmailYourEstimatePopup(driver);
    }


}
