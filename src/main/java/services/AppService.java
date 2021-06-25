package services;

import beans.App;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static api.MobileCloudApi.*;

public class AppService {
//TODO make methods non-static

    public static List<App> getAllApps(){
        Response response = mobileCloudRequestBuilder().
                addPathParameter("v1", "v1")
                .addPathParameter("spaces", "spaces")
                .addPathParameter("artifacts", "artifacts")
                .addPathParameter("bid", "0")
                .buildRequest()
                .sendRequest();
        return makeAppObjects(response);
    }

    public static String uploadApp(String filePath){
        Response response = mobileCloudRequestBuilder().
                setMethod(Method.POST)
                .addPathParameter("v1", "v1")
                .addPathParameter("spaces", "spaces")
                .addPathParameter("artifacts", "artifacts")
                .addPathParameter("bid", "0")
                .buildRequest()
                .sendRequest(filePath);
        //Returning the generated id
        return makeAppObject(response).getId();
    }


    public static void deleteAppFromCloud(String appId){
        Response response = mobileCloudRequestBuilder().
                setMethod(Method.DELETE).
                addPathParameter("v1", "v1")
                .addPathParameter("spaces", "spaces")
                .addPathParameter("artifacts", "artifacts")
                .addPathParameter("bid", "0")
                .addPathParameter("id", appId)
                .buildRequest()
                .sendRequest();
    }

    public static void installApp(String appId, String deviceSerial){
        Response response = mobileCloudRequestBuilder().
                addPathParameter("storage", "storage")
                .addPathParameter("install", "install")
                .addPathParameter("serial", deviceSerial)
                .addPathParameter("fileId", appId)
                .buildRequest()
                .sendRequest();
    }

}
