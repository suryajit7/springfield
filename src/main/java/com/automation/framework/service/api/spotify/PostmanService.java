package com.automation.framework.service.api.spotify;

import com.automation.framework.core.annotation.LazyService;
import com.automation.framework.data.entity.app.postman.Postman;
import com.automation.framework.service.BaseService;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@LazyService
public class PostmanService extends BaseService {

    private static final String WORKSPACES = "/workspaces";
    private static final String WORKSPACE_PATH_PARAM = "/workspaces/{resource_Id}";

    public Response getWorkspaces(){
        return get(WORKSPACES);
    }

    public Response getWorkspace(String resourceID){
        return get(WORKSPACE_PATH_PARAM, resourceID);
    }

    public Response post(Postman requestPayload){
        return post(WORKSPACES, requestPayload);
    }

    public Response update(Postman requestPayload, String resourceID){
        return put(WORKSPACE_PATH_PARAM, resourceID, requestPayload);
    }

    public Response delete(String resourceID){
        return delete(WORKSPACE_PATH_PARAM, resourceID);
    }
}
