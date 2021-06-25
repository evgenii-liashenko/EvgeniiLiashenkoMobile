package pageobjects.epamapp;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import pageobjects.epamapp.pages.BudgetActivityPage;
import pageobjects.epamapp.pages.RegistrationPage;
import pageobjects.epamapp.pages.StartPage;

public class EpamApp {



    public RegistrationPage registrationPage;
    public StartPage startPage;
    public BudgetActivityPage budgetActivityPage;


    public EpamApp(AppiumDriver appiumDriver) {
        PageFactory.initElements( new AppiumFieldDecorator(appiumDriver), this);
        registrationPage = new RegistrationPage(appiumDriver);
        startPage = new StartPage(appiumDriver);
        budgetActivityPage = new BudgetActivityPage(appiumDriver);
    }

}
