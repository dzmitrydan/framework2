package test;

import condition.CommonConditions;
import org.testng.annotations.Test;
import page.EmailYourEstimatePopup;
import page.GoogleCloudHomePage;
import page.PricingCalculatorPageComputeEnginePopup;
import servise.InstancesFormCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GoogleCloudCalculatorTest extends CommonConditions {

    @Test()
    public void calculatePricingOfGoogleCloud() {

        GoogleCloudHomePage googleCloudHomePage = new GoogleCloudHomePage(driver);
        PricingCalculatorPageComputeEnginePopup pricingCalculatorPageComputeEnginePopup = googleCloudHomePage
                .openHomePage()
                .searchPage("Google Cloud Platform Pricing Calculator")
                .openPricingCalculatorPage()
                .goToTabComputeEngine()
                .fillingInstancesForm(InstancesFormCreator.withEmptyWhatAreTheseInstances())
                .submitInstancesForm();

        double actualResult = pricingCalculatorPageComputeEnginePopup
                .getTotalEstimatedCostPerMonth();

        EmailYourEstimatePopup emailYourEstimatePopup = pricingCalculatorPageComputeEnginePopup
                .openEmailYourEstimatePopup();
        emailYourEstimatePopup.fillingAndSubmitEmailYourEstimateForm("email@mail.com");
        assertThat(actualResult, is(equalTo(195.67)));
    }
}
