package scenarios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import pageobjects.PageObject;
import services.AppService;
import setup.DriverInterface;
import setup.PageObjectInterface;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements DriverInterface {

    private AppService appService = new AppService();
    private String generatedAppId;

    private static AppiumDriver appiumDriver; // singleton
    PageObjectInterface pageObject;

    @Override    //getters
    public AppiumDriver getDriver() {
        return appiumDriver;
    }

    public PageObjectInterface getPageObject() {
        return pageObject;
    }

    @Parameters({"platformName", "appType", "deviceName", "udid", "browserName", "app", "appPackage", "appActivity", "bundleId"})
    @BeforeSuite(alwaysRun = true)
    public void setup(String platformName, String appType,
                      @Optional("") String deviceName,
                      @Optional("") String udid,
                      @Optional("") String browserName,
                      @Optional("") String app,
                      @Optional("") String appPackage,
                      @Optional("") String appActivity,
                      @Optional("") String bundleId) throws Exception {
        System.out.println("Before: app type - " + appType);

        //Uploading the test application to the cloud and installing it on the device
        if (appType.equals("native")) {
            switch (platformName) {
                case "Android":
                    generatedAppId = uploadAndInstall("src/main/resources/EPAMTestApp.apk", udid);
                    break;
                case "iOS":
                    generatedAppId = uploadAndInstall("src/main/resources/EPAMTestApp1.ipa", udid);
                    break;
                default: {
                    generatedAppId = "";
                    System.out.println("Cannot upload and install application on " + platformName);
                    break;
                }
            }
        }

        //This launches the app on the device
        setAppiumDriver(platformName, deviceName, udid, browserName, app, appPackage, appActivity, bundleId);
        setPageObject(appType, appiumDriver);
    }

    @AfterSuite(alwaysRun = true)
    public void teardown() throws Exception {
        if (generatedAppId != null) appService.deleteAppFromCloud(generatedAppId);
        System.out.println("After");
        appiumDriver.closeApp();
    }


    private void setAppiumDriver(String platformName, String deviceName, String udid, String browserName,
                                 String app, String appPackage, String appActivity, String bundleId) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        // mandatory Android capabilities
        capabilities.setCapability("platformName", platformName);
        capabilities.setCapability("deviceName", deviceName);
        capabilities.setCapability("udid", udid);

        if (app.endsWith(".apk")) capabilities.setCapability("app", (new File(app)).getAbsolutePath());

        capabilities.setCapability("browserName", browserName);
        capabilities.setCapability("chromedriverDisableBuildCheck", "true");

        // Capabilities for test of Android native app on EPAM Mobile Cloud
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);

        // Capabilities for test of iOS native app on EPAM Mobile Cloud
        capabilities.setCapability("bundleId", bundleId);
//if(platformName.equals("iOS")) capabilities.setCapability("automationName","XCUITest");

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

    private String uploadAndInstall(String filePath, String udid) {
        String generatedId = appService.uploadApp(filePath);
        appService.installApp(generatedId, udid);
        return generatedId;
    }


}
