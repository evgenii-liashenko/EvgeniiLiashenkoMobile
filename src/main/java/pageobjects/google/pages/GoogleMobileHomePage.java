package pageobjects.google.pages;

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


    public static final String URL = "https://www.google.com/";

    @FindBy(css = "input[type='Search']")
    public WebElement searchField;

}
