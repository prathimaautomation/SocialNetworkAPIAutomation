package data.dataprovider;

import org.testng.annotations.DataProvider;

/**
 * A data provider class to provide test data for comments related test cases
 */
public class CommentDataProvider {

    @DataProvider
    public static Object[][] validCommentsIdWithNameAndEmail() {
        return new Object[][]{
                {1, "id labore ex et quam laborum", "Eliseo@gardner.biz"},
                {2, "quo vero reiciendis velit similique earum", "Jayne_Kuhic@sydney.com"}
        };
    }
}
