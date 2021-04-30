package testcase.multipleEndpoints.comments;

import businesslayer.comment.AllCommentsBusinessLogic;
import businesslayer.post.AllPostsBusinessLogic;
import businesslayer.user.SingleUserBusinessLogic;
import data.dataprovider.UserDataProvider;
import pojo.comment.AllComments;
import pojo.comment.SingleComment;
import pojo.post.AllPosts;
import pojo.post.SinglePost;
import pojo.user.SingleUser;
import util.ObjectFactory;
import util.PropertyReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static constant.ScenarioNameConstant.VALIDATE_EMAIL_FORMAT_IN_COMMENTS_FOR_POSTS_BY_USER;
import static util.Matcher.validateEmailPattern;

/**
 * Test Class to perform all the Comments related test cases that are present in Posts made by a User
 * Endpoints that are used for Comments test cases in the following Test Class are:
 * - https://<domain>/users
 * - https://<domain>/posts?userId={userId}
 * - https://<domain>/comments?postId={postId}
 */

public class CommentsOfPostsByUserTest extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(CommentsOfPostsByUserTest.class);
    private SoftAssert softAssert = ObjectFactory.getSoftAssert();

    /*************************************************************************************
     * Test Scenario:
     * - Search for the user.
     * - Use the details fetched to make a search for the posts written by the user.
     * - For each post, fetch the comments and validate if the emails in the comment
     * section are in the proper format.
     *
     * @param userName - A valid username e.g."Samantha"
     *************************************************************************************/

    @Test(dataProvider = "validUsername", dataProviderClass = UserDataProvider.class)
    public void validateEmailFormatInCommentsForPostsByUser(String userName) {

        log.info(VALIDATE_EMAIL_FORMAT_IN_COMMENTS_FOR_POSTS_BY_USER);

        SingleUser singleUser = SingleUserBusinessLogic.getSingleUserByUserName(userName);
        log.info("Username : " + userName);
        Assert.assertNotNull(singleUser);
        int userId = singleUser.getId();
        log.info("User Id: " + userId);

        AllPosts allPostsFromUserId = AllPostsBusinessLogic.getAllPostsForUserId(userId);
        List<SinglePost> allPostsList = allPostsFromUserId.getListOfPosts();
        Assert.assertNotNull(allPostsList);

        for (SinglePost singlePost : allPostsList) {
            int postsId = singlePost.getId();

            AllComments allCommentsForPostId = AllCommentsBusinessLogic.getAllCommentsForPostId(postsId);
            List<SingleComment> allCommentsList = allCommentsForPostId.getListOfComments();
            Assert.assertNotNull(allCommentsList);

            for (SingleComment singleComment : allCommentsList) {

                String email = singleComment.getEmail();
                log.info("Validate Email: " + email);
                softAssert.assertTrue(validateEmailPattern(email));
            }
        }
        softAssert.assertAll();
    }
}
