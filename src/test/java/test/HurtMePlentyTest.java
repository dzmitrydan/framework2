package test;

import condition.CommonConditions;
import model.InstancesForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudHomePage;
import page.PricingCalculatorPage;
import page.PricingCalculatorPageComputeEnginePopup;
import servise.InstancesFormCreator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HurtMePlentyTest extends CommonConditions {

    @Test(description = "Checking that input fields are matching")
    public void matchingGettingTextInEestimateComputeEnginePopupToEnteredDataInTheFormInstancesTest() {

        InstancesForm testInstancesForm = InstancesFormCreator.withCredentialsFromProperty();

        GoogleCloudHomePage googleCloudHomePage = new GoogleCloudHomePage(driver);
        PricingCalculatorPageComputeEnginePopup pricingCalculatorPageComputeEnginePopup = googleCloudHomePage.openHomePage()
                .openPricingCalculatorPage()
                .goToTabComputeEngine()
                .fillingInstancesForm(testInstancesForm)
                .submitInstancesForm();

        assertThat(pricingCalculatorPageComputeEnginePopup.getEstimateComputeEnginePopupData(),
                samePropertyValuesAs(testInstancesForm,
                        "numberOfInstances",
                        "whatAreTheseInstancesFor",
                        "operatingSystemSoftware",
                        "checkAddGPUs",
                        "numberOfGPUs",
                        "gPUType",
                        "localSSD"));
    }

    @Test(description = "Checking that input field show markup 'invalid' (red color) when Enter not integer in the field")
    public void doesInputFieldNumberOfInstancesShowMarkupInvalidWhenEnterNotIntegerTest() {

        InstancesForm testInstancesForm = InstancesFormCreator.withNotIntegerInputValueOfNumberOfInstances();

        GoogleCloudHomePage googleCloudHomePage = new GoogleCloudHomePage(driver);
        PricingCalculatorPage pricingCalculatorPage = googleCloudHomePage.openHomePage()
                .openPricingCalculatorPage()
                .goToTabComputeEngine()
                .fillingInstancesForm(testInstancesForm);

        boolean isMarkupInvalid = pricingCalculatorPage.isMarkupInvalidWhenEnterNotInteger();

        String messageAssert = "Input field didn't show markup 'invalid' (red color) when Enter '"
                + testInstancesForm.getNumberOfInstances() + "'";

        Assert.assertTrue(isMarkupInvalid, messageAssert);
    }

}
