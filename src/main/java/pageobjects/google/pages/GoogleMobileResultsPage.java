package pageobjects.google.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleMobileResultsPage {


    //Constructor
    public GoogleMobileResultsPage(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }

    @FindBy(css = "#center_col>#topstuff+div>#rso>div[data-hveid]")
    public List<WebElement> searchResults;

    @FindBy(css = "#center_col>#topstuff+div>#rso>div[data-hveid]>div>div>div>div>a>div>span")
    public List<WebElement> foundUrls;
}
