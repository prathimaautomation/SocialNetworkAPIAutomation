package testcase.singleEndpoint.user;

import businesslayer.user.AllUsersBusinessLogic;
import businesslayer.user.SingleUserBusinessLogic;
import data.dataprovider.CommentDataProvider;
import data.dataprovider.CommonDataProvider;
import data.dataprovider.UserDataProvider;
import pojo.user.AllUsers;
import pojo.user.SingleUser;
import testcase.singleEndpoint.SingleEndpointCommon;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static constant.ScenarioNameConstant.*;
import static constant.ServiceConstant.USERS_ENDPOINT;
import static constant.ScenarioNameConstant.VALIDATE_STATUS_CODE_404;
import static io.restassured.RestAssured.*;

/**
 * Test Class to perform Users Endpoint test cases
 * Endpoints that are used in the following Test Class are
 * - https://<domain>/users
 * - https://<domain>/users/{id}
 */

public class UsersTest extends SingleEndpointCommon {

    private static final Logger log = LoggerFactory.getLogger(UsersTest.class);
    private String usersEndpoint = prop.getProperty(USERS_ENDPOINT);

    /**********************************************************
     * Send a GET request to /users. Validate that response
     * has HTTP status code 200 and Content Type JSON
     *********************************************************/

    @Test
    public void testResponseStatusCode200AndContentTypeJSON() {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + usersEndpoint);
        given().
                spec(requestSpecification).
                when().
                get(usersEndpoint).
                then().
                spec(responseSpecification);
    }

    /***********************************************************
     * Send a GET request to /users. Validate that
     * - response body contains 10 users
     ***********************************************************/

    @Test(dependsOnMethods = "testResponseStatusCode200AndContentTypeJSON")
    public void fetchListOfUsersAndAssertSize() {
        log.info(VALIDATE_LIST_OF_ITEM + usersEndpoint);

        AllUsers allUsers = AllUsersBusinessLogic.getAllUsers();
        List<SingleUser> allUsersList = allUsers.getListOfUsers();
        Assert.assertEquals(allUsersList.size(), 10);
    }

    /************************************************************
     * Send a GET request to /users/{users} and Validate
     * - response has HTTP status code 200 & Content Type is JSON
     ***********************************************************/

    @Test(dataProvider = "validId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeAndContentType(int id) {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + usersEndpoint + id);
        given().
                spec(requestSpecification).
                when().
                get(usersEndpoint + id).
                then().
                spec(responseSpecification);
    }

    /*****************************************************************************
     * Send a GET request to /users/{users} and Validate
     * - response returns the expected id, name, username, email, phone & website
     *****************************************************************************/

    @Test(dependsOnMethods = {"testResponseCodeAndContentType"},
            dataProvider = "validUserIdWithNameUsernameEmailPhoneAndWebsite",
            dataProviderClass = UserDataProvider.class)
    public void testResponseBodyWithIdTitleAndBody(int id, String name, String username,
                                                   String email, String phone, String website) {
        log.info(VALIDATE_RESPONSE_BODY + usersEndpoint + id);
        SingleUser singleUser = SingleUserBusinessLogic.getSingleUserById(id);

        softAssert.assertEquals(singleUser.getId(), id);
        softAssert.assertEquals(singleUser.getName(), name);
        softAssert.assertEquals(singleUser.getUsername(), username);
        softAssert.assertEquals(singleUser.getEmail(), email);
        softAssert.assertEquals(singleUser.getPhone(), phone);
        softAssert.assertEquals(singleUser.getWebsite(), website);
        softAssert.assertNotNull(singleUser.getAddress());
        softAssert.assertNotNull(singleUser.getAddress().getGeo());
        softAssert.assertNotNull(singleUser.getCompany());
        softAssert.assertAll();
    }

    /***************************************************************
     * Send a GET request to /users/{usersId} with invalid usersId
     * Validate that response has HTTP status code 404
     **************************************************************/

    @Test(dataProvider = "invalidId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeWithInvalidUsersId(int id) {
        log.info(VALIDATE_STATUS_CODE_404 + usersEndpoint + id);
        Response response = given().
                spec(requestSpecification).
                when().
                get(usersEndpoint + id);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
    }
}
