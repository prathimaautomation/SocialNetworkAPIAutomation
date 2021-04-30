package data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A common data provider class to provide test data used in multiple test cases
 */
public class CommonDataProvider {

    @DataProvider
    public static Object[][] validId() {
        return new Object[][]{
                {1}, {2}
        };
    }

    @DataProvider
    public static Object[][] invalidId() {
        return new Object[][]{
                {7000}, {8000}
        };
    }

    @DataProvider
    public static Object[][] validUsernameWithNumberOfPosts() {
        return new Object[][]{
                {"Antonette", 10}, {"Karianne", 10}, {"Kamren", 10}
        };
    }

}
