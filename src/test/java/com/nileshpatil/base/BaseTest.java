package com.nileshpatil.base;


import com.nileshpatil.asserts.AssertActions;
import com.nileshpatil.endpoints.APIConstants;
import com.nileshpatil.modules.restfulbooker.PayloadManager;
import com.nileshpatil.modules.vwo.VWOPayloadManager;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {


    // This is called as common to all test cases.
    // CommonToAll Testcase
    //   // Base URL, Content Type - json - common

    public RequestSpecification requestSpecification;
    public Response response;

    public ValidatableResponse validatableResponse;

    public AssertActions assertActions;
    public PayloadManager payloadManager;
    public VWOPayloadManager vwoPayloadManager;
    public JsonPath jsonPath;

    @BeforeTest
    public void setup(){

        System.out.println("Starting the test");
        payloadManager = new PayloadManager();
        vwoPayloadManager = new VWOPayloadManager();
        assertActions = new AssertActions();

//        requestSpecification = RestAssured.given();
//        requestSpecification.baseUri(APIConstants.BASE_URL);
//        requestSpecification.contentType(ContentType.JSON).log().all();


        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type" ,"application/json")
                .build().log().all();
    }

    @AfterTest
    public void tearDown(){
        System.out.println("Finished the test");
    }

    public  String getToken(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(APIConstants.BASE_URL)
                .basePath(APIConstants.AUTH_URL);
        // Setting the payload
        String payload = payloadManager.setAuthPayload();
        // Get the Token
        response = requestSpecification.contentType(ContentType.JSON).body(payload).when().post();
        String token = payloadManager.getTokenFromJSON(response.asString());
        return token;

    }

}
