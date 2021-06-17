package utils;


import org.testng.annotations.DataProvider;

import static net.andreinc.mockneat.types.enums.NameType.FIRST_NAME;
import static net.andreinc.mockneat.types.enums.PassStrengthType.MEDIUM;
import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;

public class DataProviders {

    @DataProvider
    public static Object[][] epamAppDataProvider() {
        //Generating random test data with MockNeat library
        AppTestDataSet appTestDataSet1 = new AppTestDataSet();
        appTestDataSet1.setEmail(emails().get());
        appTestDataSet1.setPassword(passwords().type(MEDIUM).get());
        appTestDataSet1.setUserName(names().type(FIRST_NAME).get());

        appTestDataSet1.setExpectedTitle("BudgetActivity");
        return new Object[][]{{appTestDataSet1}};
    }


    @DataProvider
    public static Object[][] googleDataProvider() {
        GoogleTestDataSet googleTestDataSet1 = new GoogleTestDataSet();
        googleTestDataSet1.setQuery("EPAM");
        return new Object[][]{{googleTestDataSet1}};
    }

}
