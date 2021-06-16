package pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import pageObjects._native.NativePageObject;
import pageObjects.web.WebPageObject;
import setup.PageObjectInterface;

import java.lang.reflect.Field;

public class PageObject implements PageObjectInterface {
    Object somePageObject; // it should be set of web page or EPAM Test App WebElements
    public PageObject(String appType, AppiumDriver appiumDriver) throws Exception {
        System.out.println("Current app type: "+appType);
        switch(appType){
            case "web":
                somePageObject = new WebPageObject(appiumDriver);
                break;
            case "native":
                somePageObject = new NativePageObject(appiumDriver);
                break;
            default: throw new Exception("Can't create a page object for "+appType);
        }
    }
    @Override
    public WebElement getElement(String elementName) throws NoSuchFieldException, IllegalAccessException {
        // use reflection technique
        Field field = somePageObject.getClass().getDeclaredField(elementName);
        field.setAccessible(true);
        return (WebElement) field.get(somePageObject);
    }
}
