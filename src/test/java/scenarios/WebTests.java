package scenarios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pageobjects.google.GoogleMobileWebsite;
import utils.DataProviders;
import utils.GoogleTestDataSet;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WebTests extends BaseTest {

    @Test(groups = {"web"}, description = "Make sure that we've opened IANA homepage", enabled = false)
    public void simpleWebTest() throws InterruptedException {
        getDriver().get("http://iana.org"); // open IANA homepage

        // Make sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );

        // Check IANA homepage title
        assert ((WebDriver) getDriver()).getTitle().equals("Internet Assigned Numbers Authority") : "This is not IANA homepage";

        // Log that test finished
        System.out.println("Site opening done");
    }

    @Test(dataProvider = "googleDataProvider", dataProviderClass = DataProviders.class,
            groups = {"web"}, description = "Googling 'EPAM' and checking the results", enabled = true)
    public void googleSearchTest(GoogleTestDataSet testData) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        //Opening the page and performing a search
        GoogleMobileWebsite googleSite = new GoogleMobileWebsite(getDriver());
        getDriver().get(googleSite.homePage.URL);

        //Each platform requires the query to be submitted in a different way
        String platform = getDriver().getCapabilities().getCapability("platformName").toString();
        switch (platform) {
            case "Android": {
                googleSite.homePage.searchField.sendKeys(testData.getQuery() + "\n");
            }
            break;
            case "iOS": {
                googleSite.homePage.searchField.sendKeys(testData.getQuery());
                googleSite.homePage.searchField.submit();
            }
            break;
            default:
                System.out.println("Cannot submit search query on platform " + platform);
                break;
        }

        // Making sure that page has been loaded completely
        new WebDriverWait(getDriver(), 10).until(
                wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        //Checking that the search has returned results
        assertThat("No search results are obtained", googleSite.resultsPage.searchResults.size(), is(not(0)));
        System.out.println("Google search test on " + platform + " for query " +  "\"" + testData.getQuery() + "\""+" completed");
    }


}
