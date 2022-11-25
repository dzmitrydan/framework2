package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleCloudHomePage extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://cloud.google.com";

    @FindBy(name = "q")
    private WebElement searchButton;

    public GoogleCloudHomePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudHomePage openHomePage() {
        driver.get(HOMEPAGE_URL);
        logger.info("Opened 'GoogleCloudHome Page'");
        return this;
    }

    public SearchPage searchPage(String page) {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        searchButton.sendKeys(page);
        searchButton.sendKeys(Keys.RETURN);
        logger.info("Opened 'PricingCalculator Page'");
        return new SearchPage(driver);
    }
}
