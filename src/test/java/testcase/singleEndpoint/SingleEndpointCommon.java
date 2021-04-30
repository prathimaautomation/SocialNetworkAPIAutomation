package testcase.singleEndpoint;

import util.ObjectFactory;
import util.PropertyReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import static constant.ServiceConstant.BASE_URL;

public class SingleEndpointCommon extends PropertyReader {

    protected static RequestSpecification requestSpecification;
    protected static ResponseSpecification responseSpecification;

    // Singleton instance of softAssert
    protected SoftAssert softAssert = ObjectFactory.getSoftAssert();

    /*******************************************************************
     * Create a static RequestSpecification and set BaseUrl:
     * Create a static ResponseSpecification that checks whether:
     * - the response has statusCode 200
     * - the response contentType is JSON
     ******************************************************************/
    @BeforeClass
    public static void setUp() {
        requestSpecification = new RequestSpecBuilder().
                setBaseUri(prop.getProperty(BASE_URL)).
                build();

        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(HttpStatus.SC_OK).
                expectContentType(ContentType.JSON).
                build();
    }
}
