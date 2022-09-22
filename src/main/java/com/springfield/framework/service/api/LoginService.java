package com.springfield.framework.service.api;

import com.springfield.framework.service.BaseService;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.springfield.framework.data.Constants.*;

@Service
public class LoginService extends BaseService {

    public static final String LOGIN_ROUTE = "/login";
    public static final String API_TOKEN_ROUTE = "/api/token";

    public Response getLoginResponse(){
        return get(LOGIN_ROUTE);
    }


    public Response postResponse(){

        HashMap<String, String> formParams = new HashMap<>();
        formParams.put(CLIENT_ID, SPOTIFY_CLIENT_ID);
        formParams.put(CLIENT_SECRET, SPOTIFY_CLIENT_SECRET);
        formParams.put(REFRESH_TOKEN, SPOTIFY_REFRESH_TOKEN);
        formParams.put(GRANT_TYPE, REFRESH_TOKEN);

        return post(API_TOKEN_ROUTE, formParams);
    }



}
