package pageObjects.web;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleMobileHomePage {
    //Constructor
    public GoogleMobileHomePage(AppiumDriver appiumDriver) {
        PageFactory.initElements(appiumDriver, this);
    }


    private static final String URL = "https://www.google.com/";

    @FindBy(css = "input[type='Search']")
    private WebElement searchField;

    @FindBy(css = "#center_col>#topstuff+div>#rso>div[data-hveid]")
    private List<WebElement> searchResults;


    //Getters

    public WebElement getSearchField() {
        return searchField;
    }

    public List<WebElement> getSearchResults() {
        return searchResults;
    }

    public static String getURL() {
        return URL;
    }
}
