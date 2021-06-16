package setup;

import org.openqa.selenium.WebElement;

public interface PageObjectInterface {

    WebElement getElement(String elementName) throws NoSuchFieldException, IllegalAccessException, InstantiationException;

}
