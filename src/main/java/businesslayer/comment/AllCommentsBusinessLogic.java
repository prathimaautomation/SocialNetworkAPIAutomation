package businesslayer.comment;

import pojo.comment.AllComments;
import pojo.comment.SingleComment;
import util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static constant.ServiceConstant.*;
import static io.restassured.RestAssured.when;

public class AllCommentsBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(AllCommentsBusinessLogic.class);

    /**
     * Fetch list of all comments
     * e.g. https://jsonplaceholder.typicode.com/comments
     */
    public static AllComments getAllComments() {
        String baseUrl = prop.getProperty(BASE_URL);
        String commentsEndpoint = prop.getProperty(COMMENTS_ENDPOINT);
        String url = baseUrl + commentsEndpoint;
        log.info("URL to be hit:" + url);

        Response response = when().get(url);
        return getComments(response);
    }

    /**
     * Fetch list of all comments json set respective to particular postId
     * e.g. https://jsonplaceholder.typicode.com/comments?postId=1
     */
    public static AllComments getAllCommentsForPostId(int postId) {
        String baseUrl = prop.getProperty(BASE_URL);
        String commentsEndpointQueryParamPostId = prop.getProperty(COMMENTS_ENDPOINT_QUERY_PARAM_POST_ID);
        String url = baseUrl + commentsEndpointQueryParamPostId + postId;
        log.info("URL to be hit : " + url);

        Response response = when().get(url);
        return getComments(response);
    }

    private static AllComments getComments(Response response){
        List<SingleComment> allSingleComments = Arrays.asList(response.getBody().as(SingleComment[].class));

        AllComments allComments = new AllComments();
        allComments.setListOfComments(allSingleComments);
        return allComments;
    }
}