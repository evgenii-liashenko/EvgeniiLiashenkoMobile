package setup;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageObjects.PageObject;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements DriverInterface {

    private static AppiumDriver appiumDriver; // singleton
    private static PageObjectInterface pageObject;
    //PageObjectInterface pageObject;   //the getter method invoked in simpleNativeTest() will return null and cause NPE if this field is not static

    @Override    //getters
    public AppiumDriver getDriver() { return appiumDriver; }
    public PageObjectInterface getPageObject() {
        return pageObject;
    }

    @Parameters({"platformName","appType","deviceName","browserName","app"})
    @BeforeSuite(alwaysRun = true)
    public void setup(String platformName, String appType, String deviceName, @Optional("") String browserName, @Optional("") String app) throws Exception {
        System.out.println("Before: app type - "+appType);
        setAppiumDriver(platformName, deviceName, browserName, app);
        setPageObject(appType, appiumDriver);

        System.out.println("Checking the getter method from BaseTest");
        String message2 = (getPageObject() == null)?
                ("\n!!! getPageObject() invoked from setup() returned null !!!\n")
                :("[OK] getPageObject() invoked from setup() returned an object");
        System.out.println(message2);
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() throws Exception {
        System.out.println("After");
        appiumDriver.closeApp();

        System.out.println("Checking the getter method from BaseTest");
        String message2 = (getPageObject() == null)?
                ("\n!!! getPageObject() invoked from teardown() returned null !!!\n")
                :("[OK] getPageObject() invoked from teardown() returned an object");
        System.out.println(message2);
    }

    private void setAppiumDriver(String platformName, String deviceName, String browserName, String app){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //mandatory Android capabilities
        capabilities.setCapability("platformName",platformName);
        capabilities.setCapability("deviceName",deviceName);

        if(app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck","true");

        try {
            appiumDriver = new AppiumDriver(new URL(System.getProperty("ts.appium")), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Timeouts tuning
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    private void setPageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        System.out.println("Setting page object");
        pageObject = new PageObject(appType, appiumDriver);
    }

}
