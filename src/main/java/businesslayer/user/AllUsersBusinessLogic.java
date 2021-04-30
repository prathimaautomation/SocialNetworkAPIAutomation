package businesslayer.user;

import pojo.user.AllUsers;
import pojo.user.SingleUser;
import util.PropertyReader;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static constant.ServiceConstant.BASE_URL;
import static constant.ServiceConstant.USERS_ENDPOINT;
import static io.restassured.RestAssured.when;

public class AllUsersBusinessLogic extends PropertyReader {

    private static final Logger log = LoggerFactory.getLogger(AllUsersBusinessLogic.class);

    /**
     * Fetch list of all users
     * e.g. https://jsonplaceholder.typicode.com/users
     */
    public static AllUsers getAllUsers() {
        String baseUrl = prop.getProperty(BASE_URL);
        String usersEndpoint = prop.getProperty(USERS_ENDPOINT);
        String url = baseUrl + usersEndpoint;
        log.info("URL to be hit : " + url);

        Response response = when().get(url);
        List<SingleUser> usersList = Arrays.asList(response.getBody().as(SingleUser[].class));

        AllUsers allUsers = new AllUsers();
        allUsers.setListOfUsers(usersList);
        return allUsers;
    }
}

