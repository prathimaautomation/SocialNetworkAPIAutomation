package data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for users related test cases
 */

public class UserDataProvider {

    @DataProvider
    public static Object[][] validUserIdWithNameUsernameEmailPhoneAndWebsite() {
        return new Object[][]{
                {1, "Leanne Graham", "Bret", "Sincere@april.biz", "1-770-736-8031 x56442", "hildegard.org"},
                {2, "Ervin Howell", "Antonette", "Shanna@melissa.tv", "010-692-6593 x09125", "anastasia.net"}
        };
    }

    @DataProvider
    public static Object[][] validUsername() {
        return new Object[][]{
                {"Samantha"},
        };
    }
}
