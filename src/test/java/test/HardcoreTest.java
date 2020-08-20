package test;

import driver.DriverSingleton;
import googlecloud.model.InstancesForm;
import googlecloud.page.EmailYourEstimatePopup;
import googlecloud.page.GoogleCloudHomePage;
import googlecloud.page.HomePage10MinuteMail;
import googlecloud.page.PricingCalculatorPageComputeEnginePopup;
import googlecloud.servise.InstancesFormCreator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.WebBrowser;

public class HardcoreTest extends CommonConditions {

    private PricingCalculatorPageComputeEnginePopup pricingCalculatorPageComputeEnginePopup;


    @BeforeMethod()
    public void browserSetup(){

        driver = DriverSingleton.getDriver();

        InstancesForm testInstancesForm = InstancesFormCreator.withEmptyWhatAreTheseInstances();

        GoogleCloudHomePage googleCloudHomePage = new GoogleCloudHomePage(driver);
        pricingCalculatorPageComputeEnginePopup = googleCloudHomePage.openHomePage()
                .openPricingCalculatorPage()
                .goToTabComputeEngine()
                .fillingAndSubmitInstancesForm(testInstancesForm);
    }

    @Test
    public void totalEstimatedCostInEmailIsMatchToPricingCalculatorPage(){

        double totalEstimatedCostTextOnPricingCalculatorPage = pricingCalculatorPageComputeEnginePopup.getTotalEstimatedCostPerMonth();

        EmailYourEstimatePopup emailYourEstimatePopup = pricingCalculatorPageComputeEnginePopup.openEmailYourEstimatePopup();

        WebBrowser webBrowser = new WebBrowser();
        webBrowser.openNewWebBrowserTab(driver);

        HomePage10MinuteMail homePage10MinuteMail = new HomePage10MinuteMail(driver);
        homePage10MinuteMail.openHomePage10MinuteMail();
        String mailAddress = homePage10MinuteMail.getMailAddress();

        webBrowser.openExistingWebBrowserTab(driver, emailYourEstimatePopup.getWebBrowserTab());
        emailYourEstimatePopup.fillingAndSubmitEmailYourEstimateForm(mailAddress);


        webBrowser.openExistingWebBrowserTab(driver, homePage10MinuteMail.getWebBrowserTab());
        double actualTotalEstimatedMonthlyCostInEmail = homePage10MinuteMail.getTotalEstimatedMonthlyCost();

        Assert.assertEquals(actualTotalEstimatedMonthlyCostInEmail, totalEstimatedCostTextOnPricingCalculatorPage);
    }


}
