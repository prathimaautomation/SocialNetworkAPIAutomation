package data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for posts related test cases
 */
public class PostDataProvider {

    @DataProvider
    public static Object[][] validPostsIdWithTitle() {
        return new Object[][]{
                {1, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"},
                {2, "qui est esse"}
        };
    }
}
