package scenarios;

import org.testng.annotations.Test;
import setup.BaseTest;

public class NativeTests extends BaseTest {

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button")
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        System.out.println("RUNNING THE TEST");
        System.out.println("Checking the getter method from NativeMobileTests");

        //Upcoming NPE warning
        String message = (getPageObject() == null)?
                ("\n!!! getPageObject() invoked from simpleNativeTest() returned null !!!\n")
                :("[OK] getPageObject() invoked from simpleNativeTest() returned an object");
        System.out.println(message);

        //NPE was happening here
        getPageObject().getElement("signInBtn").click();
        System.out.println("Simplest Android native test done");
    }
}
