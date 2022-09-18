package com.springfield.framework.service.spotify.api;

import com.springfield.framework.service.spotify.BaseService;
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
        formParams.clear();
        formParams.put(CLIENT_ID, SPOTIFY_CLIENT_ID);
        formParams.put(CLIENT_SECRET, SPOTIFY_CLIENT_SECRET);
        formParams.put(REFRESH_TOKEN, "AQAHwXdqmKQlWrUpSzBt5hm5420rv_g1Rf3jb8adJeXP0E8Nr5TkaN3pUesioaPKdi2OPwkASxXpyJqFB2vBH9Sr_nqzwgSv-q1a-KXjxJgCK2ojrazsTQsg_JO61MtzMg0");
        formParams.put(GRANT_TYPE, REFRESH_TOKEN);

        return post(API_TOKEN_ROUTE, formParams);
    }



}
