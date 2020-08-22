package test;

import condition.CommonConditions;
import model.InstancesForm;
import page.EmailYourEstimatePopup;
import page.GoogleCloudHomePage;
import page.HomePage10MinuteMail;
import page.PricingCalculatorPageComputeEnginePopup;
import servise.InstancesFormCreator;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.WebBrowser;

public class HardcoreTest extends CommonConditions {


    @Test
    public void totalEstimatedCostInEmailIsMatchToPricingCalculatorPage(){

        InstancesForm testInstancesForm = InstancesFormCreator.withEmptyWhatAreTheseInstances();

        GoogleCloudHomePage googleCloudHomePage = new GoogleCloudHomePage(driver);
        PricingCalculatorPageComputeEnginePopup pricingCalculatorPageComputeEnginePopup = googleCloudHomePage.openHomePage()
                .openPricingCalculatorPage()
                .goToTabComputeEngine()
                .fillingAndSubmitInstancesForm(testInstancesForm);

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
