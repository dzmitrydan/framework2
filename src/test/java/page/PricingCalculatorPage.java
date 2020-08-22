package page;

import com.paulhammant.ngwebdriver.ByAngularModel;
import com.paulhammant.ngwebdriver.ByAngularPartialButtonText;
import com.paulhammant.ngwebdriver.NgWebDriver;
import model.InstancesForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import util.Checkbox;
import util.Dropdown;


public class PricingCalculatorPage extends AbstractPage {

    private NgWebDriver ngDriver;

    private final Logger logger = LogManager.getRootLogger();


    public PricingCalculatorPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameMyFrame));
        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
    }


    @FindBy(xpath ="//iframe[contains(@name, 'goog')]")
    private WebElement frame;

    @FindBy(id = "myFrame")
    private WebElement frameMyFrame;

    @FindBy(xpath ="//*[contains(@title, 'Compute Engine')]")
    private WebElement tabComputeEngine;

    @FindBy(name = "quantity")
    private WebElement inputNumberOfInstances;

    @ByAngularModel.FindBy(model = "listingCtrl.computeServer.label")
    private WebElement inputWhatAreTheseInstancesFor;

    @ByAngularModel.FindBy(model = "listingCtrl.computeServer.addGPUs")
    private WebElement checkboxAddGPUs;

    @ByAngularPartialButtonText.FindBy(partialButtonText = "Add to Estimate")
    private WebElement buttonAddToEstimate;


    public PricingCalculatorPage goToTabComputeEngine(){
        executor.executeScript("arguments[0].click();", tabComputeEngine);
        return this;
    }


    public PricingCalculatorPageComputeEnginePopup fillingAndSubmitInstancesForm(InstancesForm instancesForm){

        inputNumberOfInstances.sendKeys(instancesForm.getNumberOfInstances());

        inputWhatAreTheseInstancesFor.sendKeys(instancesForm.getWhatAreTheseInstancesFor());

        Dropdown.selecItemByText(instancesForm.getOperatingSystemSoftware(), driver, executor);

        Dropdown.selecItemByText(instancesForm.getMachineClass(), driver, executor);

        Dropdown.selecItemByText(instancesForm.getMachineType(), driver, executor);

        Checkbox.check(checkboxAddGPUs, instancesForm.isCheckAddGPUs(), executor);

        Dropdown.selecItemByText(instancesForm.getNumberOfGPUs(), driver, executor);

        Dropdown.selecItemByText(instancesForm.getgPUType(), driver, executor);

        Dropdown.selecItemByText(instancesForm.getLocalSSD(), driver, executor);

        Dropdown.selecItemByText(instancesForm.getDatacenterLocation(), driver, executor);

        Dropdown.selecItemByText(instancesForm.getCommitedUsage(), driver, executor);

        buttonAddToEstimate.submit();

        logger.info("Data on instances have been added for estimation: \n" + instancesForm.toString());

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("compute"), "Compute Engine"));

        return new PricingCalculatorPageComputeEnginePopup(driver);
    }


}
