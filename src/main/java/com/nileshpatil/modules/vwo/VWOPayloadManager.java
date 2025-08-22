package com.nileshpatil.modules.vwo;

import com.google.gson.Gson;
import com.nileshpatil.pojos.RequestPOJO.vwo.VWOLoginRequest;
import com.nileshpatil.pojos.ResponsePOJO.vwo.responsePOJO.InvalidLoginResponse;
import com.nileshpatil.pojos.ResponsePOJO.vwo.responsePOJO.LoginResponse;

public class VWOPayloadManager {

    Gson gson;

    // Java Object -> JSON
    public String setLoginDataInvalid(){
        VWOLoginRequest loginRequest = new VWOLoginRequest();
        loginRequest.setUsername("atb@01234.space");
//        loginRequest.setPassword("Atb@01234.space");
        loginRequest.setPassword("Wrong");
        loginRequest.setRemember(false);
        loginRequest.setRecaptchaResponseField("");

        gson = new Gson();
        String jsonPayloadString = gson.toJson(loginRequest);
        System.out.println("Payload Login to the --> " + jsonPayloadString);
        return jsonPayloadString;

    }

    public String setLoginDataValid(){
        VWOLoginRequest loginRequest = new VWOLoginRequest();
        loginRequest.setUsername("atb@01234.space");
        loginRequest.setPassword("Atb@01234.space");
//        loginRequest.setPassword("Wrong");
        loginRequest.setRemember(false);
        loginRequest.setRecaptchaResponseField("");

        gson = new Gson();
        String jsonPayloadString = gson.toJson(loginRequest);

        System.out.println("Payload Login to the --> " + jsonPayloadString);
        return jsonPayloadString;

    }

    public String getLoginDataInvalid(String loginResponseEx){
        gson = new Gson();
        InvalidLoginResponse loginResponse = gson.fromJson(loginResponseEx,InvalidLoginResponse.class);
        return loginResponse.getMessage();

    }

    public LoginResponse getLoginDataValid(String loginResponseEx){
        gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(loginResponseEx, LoginResponse.class);
        return loginResponse;

    }
}
