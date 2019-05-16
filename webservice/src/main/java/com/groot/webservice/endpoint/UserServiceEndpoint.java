package com.groot.webservice.endpoint;

import com.groot.webservice.common.Constants;
import com.groot.webservice.model.FindUserByIdRequest;
import com.groot.webservice.model.FindUserByIdResponse;
import com.groot.webservice.model.GetUserByIdRequest;
import com.groot.webservice.model.GetUserByIdResponse;
import org.springframework.ws.server.endpoint.annotation.*;

import java.util.Date;

@Endpoint("userServiceEnpoint")
public class UserServiceEndpoint {

    @PayloadRoot(namespace = Constants.US_NAMESPACE, localPart = "GetUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest request) throws Exception {
        System.out.println("GetUserById: "+request.getUserId());
        GetUserByIdResponse response = new GetUserByIdResponse();
        response.setUsername("Freud");
        response.setGender("Male");
        response.setLocation("Get");
        response.setBirthday(new Date());
        return response;
    }


    @PayloadRoot(namespace = Constants.US_NAMESPACE, localPart = "FindUserByIdRequest")
    @ResponsePayload
    public FindUserByIdResponse findUserById(@RequestPayload FindUserByIdRequest request) throws Exception {
        System.out.println("FindUserById: "+request.getUserId());
        FindUserByIdResponse response = new FindUserByIdResponse();
        response.setUsername("Freud");
        response.setLocation("Find");
        response.setGender("Male");
        response.setBirthday(new Date());
        return response;
    }
}
