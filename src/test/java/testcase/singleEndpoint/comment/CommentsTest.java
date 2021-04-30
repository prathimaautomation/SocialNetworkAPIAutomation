package testcase.singleEndpoint.comment;

import businesslayer.comment.AllCommentsBusinessLogic;
import businesslayer.comment.SingleCommentBusinessLogic;
import data.dataprovider.CommentDataProvider;
import data.dataprovider.CommonDataProvider;
import pojo.comment.AllComments;
import pojo.comment.SingleComment;
import testcase.singleEndpoint.SingleEndpointCommon;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static constant.ScenarioNameConstant.*;
import static constant.ServiceConstant.COMMENTS_ENDPOINT;
import static io.restassured.RestAssured.given;

/**
 * Test Class to perform Comments Endpoint test cases
 * Endpoints that are used in the following Test Class are
 * - https://<domain>/comments
 * - https://<domain>/comments/{id}
 */

public class CommentsTest extends SingleEndpointCommon {

    private static final Logger log = LoggerFactory.getLogger(CommentsTest.class);
    private String commentsEndpoint = prop.getProperty(COMMENTS_ENDPOINT);

    /**********************************************************
     * Send a GET request to /comments. Validate that response
     * has HTTP status code 200 and Content Type JSON
     *********************************************************/

    @Test
    public void testResponseStatusCode200AndContentTypeJSON() {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + commentsEndpoint);
        given().
                spec(requestSpecification).
                when().
                get(commentsEndpoint).
                then().
                spec(responseSpecification);
    }

    /***********************************************************
     * Send a GET request to /comments. Validate that
     * - response body contains list of 500 comments
     ***********************************************************/

    @Test(dependsOnMethods = "testResponseStatusCode200AndContentTypeJSON")
    public void fetchListOfCommentsAndAssertSize() {
        log.info(VALIDATE_LIST_OF_ITEM + commentsEndpoint);

        AllComments allComments = AllCommentsBusinessLogic.getAllComments();
        List<SingleComment> allCommentsList = allComments.getListOfComments();
        Assert.assertEquals(allCommentsList.size(), 500);
    }

    /************************************************************
     * Send a GET request to /comments/{commentsId} and Validate
     * - response has HTTP status code 200 & Content Type is JSON
     ***********************************************************/

    @Test(dataProvider = "validId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeAndContentType(int id) {
        log.info(VALIDATE_STATUS_CODE_200_AND_CONTENT_TYPE_JSON + commentsEndpoint + id);
        given().
                spec(requestSpecification).
                when().
                get(commentsEndpoint + id).
                then().
                spec(responseSpecification);
    }

    /************************************************************
     * Send a GET request to /comments/{commentsId} and Validate
     * - response returns the expected id, name, email & body
     ***********************************************************/

    @Test(dependsOnMethods = {"testResponseCodeAndContentType"},
            dataProvider = "validCommentsIdWithNameAndEmail",
            dataProviderClass = CommentDataProvider.class)
    public void testResponseBodyWithIdNameAndEmail(int id, String name, String email) {
        log.info(VALIDATE_RESPONSE_BODY + commentsEndpoint + id);

        SingleComment singleComment = SingleCommentBusinessLogic.getSingleCommentById(id);

        softAssert.assertEquals(singleComment.getId(), id);
        softAssert.assertEquals(singleComment.getName(), name);
        softAssert.assertEquals(singleComment.getEmail(), email);
        softAssert.assertNotNull(singleComment.getBody());
        softAssert.assertAll();
    }

    /***********************************************************************
     * Send a GET request to /comments/{commentsId} with invalid commentsId
     * Validate that response has HTTP status code 404
     **********************************************************************/

    @Test(dataProvider = "invalidId", dataProviderClass = CommonDataProvider.class)
    public void testResponseCodeWithInvalidCommentsId(int id) {
        log.info(VALIDATE_STATUS_CODE_404 + commentsEndpoint + id);
        Response response = given().
                spec(requestSpecification).
                when().
                get(commentsEndpoint + id);
        Assert.assertEquals(response.statusCode(), HttpStatus.SC_NOT_FOUND);
    }
}
