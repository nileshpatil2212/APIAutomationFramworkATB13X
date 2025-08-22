package com.nileshpatil.tests.vwo;

import com.nileshpatil.base.BaseTest;
import com.nileshpatil.endpoints.APIConstants;
import io.restassured.RestAssured;

public class TestVWOLoginInvalid extends BaseTest {

    public void test_VWO_Login_Negative(){
        requestSpecification.baseUri(APIConstants.APP_VWO_URL);
        response = RestAssured.given(requestSpecification)
                .when().body(vwoPayloadManager.setLoginDataInvalid()).log().all()
                .post();

        assertActions.verifyStatusCode(response,401);

        String msg = vwoPayloadManager.getLoginDataInvalid(response.asString());
        assertActions.verifyStringKey(msg,"Invalid User");

    }
}
