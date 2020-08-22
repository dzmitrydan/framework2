package test;

import driver.DriverSingleton;
import model.InstancesForm;
import page.GoogleCloudHomePage;
import page.PricingCalculatorPageComputeEnginePopup;
import servise.InstancesFormCreator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.StringExtractor;

public class HurtMePlentyTest {
    protected WebDriver driver;

    private PricingCalculatorPageComputeEnginePopup pricingCalculatorPageComputeEnginePopup;
    private InstancesForm testInstancesForm;

    @BeforeMethod
    public void browserSetup(){
        driver = DriverSingleton.getDriver();
        testInstancesForm = InstancesFormCreator.withEmptyWhatAreTheseInstances();

        GoogleCloudHomePage googleCloudHomePage = new GoogleCloudHomePage(driver);
        pricingCalculatorPageComputeEnginePopup = googleCloudHomePage.openHomePage()
                .openPricingCalculatorPage()
                .goToTabComputeEngine()
                .fillingAndSubmitInstancesForm(testInstancesForm);
    }

    @Test
    public void matchingTextVMClassToInputtedInTheForm(){
        String actualVMClassText = pricingCalculatorPageComputeEnginePopup.getVMClass();
        Assert.assertEquals(StringExtractor.stringCapitalize(StringExtractor.deleteTextBeforeColon(actualVMClassText)), testInstancesForm.getMachineClass());
    }

    @Test
    public void matchingTextInstanceTypeToInputtedInTheForm(){
        String actualInstanceTypeText = pricingCalculatorPageComputeEnginePopup.getInstanceType();
        Assert.assertEquals(StringExtractor.deleteTextBeforeColon(actualInstanceTypeText), StringExtractor.deleteTextInTheBrackets(testInstancesForm.getMachineType()));
    }

    @Test
    public void matchingTextRegionToInputtedInTheForm(){
        String actualRegionText = pricingCalculatorPageComputeEnginePopup.getRegion();
        Assert.assertEquals(StringExtractor.deleteTextBeforeColon(actualRegionText) , StringExtractor.deleteTextInTheBrackets(testInstancesForm.getDatacenterLocation()));
    }

    @Test
    public void matchingTextCommitmentTermToInputtedInTheForm(){
        String actualCommitmentTermText = pricingCalculatorPageComputeEnginePopup.getCommitmentTerm();
        Assert.assertEquals(StringExtractor.deleteTextBeforeColon(actualCommitmentTermText), testInstancesForm.getCommitedUsage());
    }


    @AfterMethod(alwaysRun = true)
    public void browserClose(){
        DriverSingleton.closeDriver();
    }

}
