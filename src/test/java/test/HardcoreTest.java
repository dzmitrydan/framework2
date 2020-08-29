package test;

import condition.CommonConditions;
import model.InstancesForm;
import org.testng.annotations.Test;
import page.EmailYourEstimatePopup;
import page.GoogleCloudHomePage;
import page.HomePage10MinuteMail;
import page.PricingCalculatorPageComputeEnginePopup;
import servise.InstancesFormCreator;
import util.WebBrowser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HardcoreTest extends CommonConditions {

    @Test(description = "Checking that 'Total Estimated Cost' getting in the Email is matching to got in the 'Pricing Calculator Page'")
    public void totalEstimatedCostInEmailIsMatchToPricingCalculatorPage() {

        InstancesForm testInstancesForm = InstancesFormCreator.withEmptyWhatAreTheseInstances();

        GoogleCloudHomePage googleCloudHomePage = new GoogleCloudHomePage(driver);
        PricingCalculatorPageComputeEnginePopup pricingCalculatorPageComputeEnginePopup = googleCloudHomePage
                .openHomePage()
                .openPricingCalculatorPage()
                .goToTabComputeEngine()
                .fillingInstancesForm(testInstancesForm)
                .submitInstancesForm();

        double totalEstimatedCostTextOnPricingCalculatorPage = pricingCalculatorPageComputeEnginePopup
                .getTotalEstimatedCostPerMonth();

        EmailYourEstimatePopup emailYourEstimatePopup = pricingCalculatorPageComputeEnginePopup
                .openEmailYourEstimatePopup();

        WebBrowser webBrowser = new WebBrowser();
        webBrowser.openNewWebBrowserTab(driver);

        HomePage10MinuteMail homePage10MinuteMail = new HomePage10MinuteMail(driver);
        homePage10MinuteMail.openHomePage10MinuteMail();
        String mailAddress = homePage10MinuteMail.getMailAddress();

        webBrowser.openExistingWebBrowserTab(driver, emailYourEstimatePopup.getWebBrowserTab());
        emailYourEstimatePopup.fillingAndSubmitEmailYourEstimateForm(mailAddress);

        webBrowser.openExistingWebBrowserTab(driver, homePage10MinuteMail.getWebBrowserTab());
        double actualTotalEstimatedMonthlyCostInEmail = homePage10MinuteMail.getTotalEstimatedMonthlyCost();

        assertThat(actualTotalEstimatedMonthlyCostInEmail, is(equalTo(totalEstimatedCostTextOnPricingCalculatorPage)));
    }

}
