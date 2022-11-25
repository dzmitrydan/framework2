package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage extends AbstractPage {

    @FindBy(xpath = "//div[@class='gsc-thumbnail-inside']//a[@class='gs-title']/b[text()='Google Cloud Pricing Calculator']")
    private WebElement linkToPricingCalculatorPage;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public PricingCalculatorPage openPricingCalculatorPage() {
        wait.until(ExpectedConditions.elementToBeClickable(linkToPricingCalculatorPage));
        linkToPricingCalculatorPage.click();
        return new PricingCalculatorPage(driver);
    }

}
