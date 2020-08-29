package page;

import com.google.common.base.Function;
import com.paulhammant.ngwebdriver.ByAngularModel;
import com.paulhammant.ngwebdriver.ByAngularPartialButtonText;
import com.paulhammant.ngwebdriver.NgWebDriver;
import model.InstancesForm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import util.Checkbox;
import util.Dropdown;

import java.time.Duration;

public class PricingCalculatorPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();
    private NgWebDriver ngDriver;
    private Wait<WebDriver> fluentWait;

    private final By frameGoogLocator = By.xpath("//devsite-iframe/iframe");
    private final By tabComputeEngineLocator = By.xpath("//*[contains(@title, 'Compute Engine')]");
    private final By inputContainerNumberOfInstancesLocator = By.xpath("//input[@ng-model='listingCtrl.computeServer.quantity']/parent::md-input-container");

    @FindBy(id = "myFrame")
    private WebElement frameMyFrame;

    @ByAngularModel.FindBy(model = "listingCtrl.computeServer.quantity")
    private WebElement inputNumberOfInstances;

    @ByAngularModel.FindBy(model = "listingCtrl.computeServer.label")
    private WebElement inputWhatAreTheseInstancesFor;

    @ByAngularModel.FindBy(model = "listingCtrl.computeServer.addGPUs")
    private WebElement checkboxAddGPUs;

    @ByAngularPartialButtonText.FindBy(partialButtonText = "Add to Estimate")
    private WebElement buttonAddToEstimate;

    public PricingCalculatorPage(WebDriver driver) {
        super(driver);
        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
    }

    public PricingCalculatorPage goToTabComputeEngine() {

        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timeout for waiting search web element was exceeded");

        WebElement frameGoog = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(frameGoogLocator);
            }
        });
        driver.switchTo().frame(frameGoog);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameMyFrame));

        WebElement tabComputeEngine = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(tabComputeEngineLocator);
            }
        });

        executor.executeScript("arguments[0].click();", tabComputeEngine);
        return this;
    }

    public PricingCalculatorPage fillingInstancesForm(InstancesForm instancesForm) {
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
        logger.info("Data in the 'Instances' Form: " + instancesForm.toString());
        return this;
    }

    public PricingCalculatorPageComputeEnginePopup submitInstancesForm() {
        buttonAddToEstimate.submit();
        logger.info("Data on instances have been added for estimation");
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("compute"), "Compute Engine"));
        return new PricingCalculatorPageComputeEnginePopup(driver);
    }

    public boolean isMarkupInvalidWhenEnterNotInteger() {

        WebElement inputContainerNumberOfInstances = fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(inputContainerNumberOfInstancesLocator);
            }
        });

        String[] classAttributes = inputContainerNumberOfInstances.getAttribute("class").split(" ");

        boolean inputContainerNumberOfInstancesHasClassValueInvalid = false;

        for (String attribute : classAttributes) {
            if (attribute.equals("md-input-invalid")) {
                inputContainerNumberOfInstancesHasClassValueInvalid = true;
                break;
            }
        }
        return inputContainerNumberOfInstancesHasClassValueInvalid;
    }

}
