package page;

import com.google.common.base.Function;
import com.paulhammant.ngwebdriver.ByAngularModel;
import com.paulhammant.ngwebdriver.ByAngularPartialButtonText;
import com.paulhammant.ngwebdriver.NgWebDriver;
import model.InstancesForm;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class PricingCalculatorPage extends AbstractPage {

    private NgWebDriver ngDriver;
    private Wait<WebDriver> fluentWait;
    private final By frameGoogLocator = By.xpath("//devsite-iframe/iframe");
    private final By tabComputeEngineLocator = By.xpath("//*[contains(@title, 'Compute Engine')]");

    @FindBy(id = "myFrame")
    private WebElement frameMyFrame;

    @ByAngularModel.FindBy(model = "listingCtrl.computeServer.quantity")
    private WebElement inputNumberOfInstances;

    @ByAngularModel.FindBy(model = "listingCtrl.computeServer.label")
    private WebElement inputWhatAreTheseInstancesFor;

    @ByAngularPartialButtonText.FindBy(partialButtonText = "Add to Estimate")
    private WebElement buttonAddToEstimate;

    @FindBy(id = "compute")
    private WebElement popupComputeEngine;

    public PricingCalculatorPage(WebDriver driver) {
        super(driver);
        ngDriver = new NgWebDriver((JavascriptExecutor) driver);
        ngDriver.waitForAngularRequestsToFinish();
    }

    public PricingCalculatorPage goToTabComputeEngine() {

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Timeout for waiting search web element was exceeded");

        WebElement frameGoog = fluentWait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(frameGoogLocator));
        driver.switchTo().frame(frameGoog);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameMyFrame));

        WebElement tabComputeEngine = fluentWait.until((Function<WebDriver, WebElement>) driver -> driver.findElement(tabComputeEngineLocator));
        clickWebElement(tabComputeEngine);
        return this;
    }

    public PricingCalculatorPage fillingInstancesForm(InstancesForm instancesForm) {
        inputNumberOfInstances.sendKeys(instancesForm.getNumberOfInstances());
        inputWhatAreTheseInstancesFor.sendKeys(instancesForm.getWhatAreTheseInstancesFor());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("Data in the 'Instances' Form");
        return this;
    }

    public PricingCalculatorPageComputeEnginePopup submitInstancesForm() {
        buttonAddToEstimate.submit();
        logger.info("Data on instances have been added for estimation");
        wait.until(ExpectedConditions.textToBePresentInElement(popupComputeEngine, "Compute Engine"));
        return new PricingCalculatorPageComputeEnginePopup(driver);
    }

}
