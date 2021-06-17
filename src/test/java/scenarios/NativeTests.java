package scenarios;

import org.testng.annotations.Test;
import pageobjects.epamapp.EpamApp;
import utils.DataProviders;
import utils.AppTestDataSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class NativeTests extends BaseTest {

    @Test(groups = {"native"}, description = "This simple test just click on the Sign In button", enabled = false)
    public void simpleNativeTest() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        pageObject.getElement("signInBtn").click();
        System.out.println("Simplest Android native test done");
    }


    @Test(dataProvider = "epamAppDataProvider", dataProviderClass = DataProviders.class,
            groups = {"native"}, description = "The GUI test for EPAMTestApp from task 3", enabled = true)
    public void epamAppTest(AppTestDataSet testData) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        //Executing test steps
        EpamApp app = new EpamApp(getDriver());
        app.startPage.registerButton.click();
        app.registrationPage.createUser(testData.getEmail(), testData.getUserName(), testData.getPassword(), testData.getPassword());
        app.startPage.authotizeUser(testData.getEmail(), testData.getPassword());

        //Verifying that BudgetActivity page opened
        assertThat(app.budgetActivityPage.getTitle(), is(testData.getExpectedTitle()));
        System.out.println("EPAM App test completed");
    }

}
