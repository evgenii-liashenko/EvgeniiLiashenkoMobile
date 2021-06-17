package pageobjects.google;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.PageFactory;
import pageobjects.google.pages.GoogleMobileHomePage;
import pageobjects.google.pages.GoogleMobileResultsPage;

public class GoogleMobileWebsite {
    public GoogleMobileHomePage homePage;
    public GoogleMobileResultsPage resultsPage;

    public GoogleMobileWebsite(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
        this.homePage = new GoogleMobileHomePage(appiumDriver);
        this.resultsPage = new GoogleMobileResultsPage(appiumDriver);
    }

}
