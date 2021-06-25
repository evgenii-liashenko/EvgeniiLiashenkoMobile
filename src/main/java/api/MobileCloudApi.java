package api;

import java.io.File;
import java.net.URI;

import beans.App;
import utils.PropertyReader;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;



import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MobileCloudApi {

    //Base endpoint URL
    public static final URI MOBILE_CLOUD_BASE_URL = URI.create(PropertyReader.getApiProperties().getProperty("base_url"));      //https://mobilecloud.epam.com/automation/api/

    //API access token
    public static final String API_TOKEN = PropertyReader.getApiProperties().getProperty("token");

    //Request variables
    protected Method requestMethod;
    protected Map<String, String> pathParameters;
    protected Map<String, String> queryParameters;

    //Constructor
    public MobileCloudApi(Method requestMethod, Map<String, String> pathParameters, Map<String, String> queryParameters) {
        this.requestMethod = requestMethod;
        this.pathParameters = pathParameters;
        this.queryParameters = queryParameters;
    }

    //Builder implementation
    public static class ApiRequestBuilder {
        private Method requestMethod = Method.GET;      //Default method in case no method is set
        private Map<String, String> builderQueryParams = new HashMap<>();
        private Map<String, String> builderPathParams = new LinkedHashMap<>();

        public ApiRequestBuilder setMethod (Method method){
            requestMethod = method;
            return this;
        }

        public ApiRequestBuilder addPathParameter(String parameterName, String parameterValue){
            builderPathParams.put(parameterName, parameterValue);
            return this;
        }

        public ApiRequestBuilder setFilter(String fileExtension){
            builderQueryParams.put("filter", fileExtension);
            return this;
        }

        public MobileCloudApi buildRequest(){
            return new MobileCloudApi(requestMethod, builderPathParams, builderQueryParams);
        }
    }

    //Builder instantiation
    public static ApiRequestBuilder mobileCloudRequestBuilder(){
        return new ApiRequestBuilder();
    }

    //API interaction. Empty body
    public Response sendRequest() {
        String pathStr = "";
        for (String key : pathParameters.keySet()) {
            pathStr += "{" + key + "}/";
        }
        pathStr = pathStr.substring(0, pathStr.length() - 1);
        return RestAssured
                .given(requestSpecification()).log().all()
                .header("Authorization", "Bearer " + API_TOKEN)
                .pathParams(pathParameters)
                .queryParams(queryParameters)
                .request(requestMethod ,pathStr)
                .prettyPeek();
    }

    //API interaction. Body contains a file in form-data
    public Response sendRequest(String filePath) {
        String pathStr = "";
        for (String key : pathParameters.keySet()) {
            pathStr += "{" + key + "}/";
        }
        pathStr = pathStr.substring(0, pathStr.length() - 1); // removing the slash
        return RestAssured
                .given(requestSpecification()).log().all()
                .contentType("multipart/form-data")
                .multiPart("file", new File(filePath))
                .multiPart("fileName", new File(filePath).getName())
                .multiPart("contentType", "application/zip")
                .header("Authorization", "Bearer " + API_TOKEN)
                .pathParams(pathParameters)
                .queryParams(queryParameters)
                .request(requestMethod ,pathStr)
                .prettyPeek();
    }


    //Conversion of deserialized json from Response to App
    public static List<App> makeAppObjects(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<List<App>>() {}.getType());
    }

    public static App makeAppObject(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<App>() {}.getType());
    }

    //Request specification
    public static RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .setBaseUri(MOBILE_CLOUD_BASE_URL)
                .build();
    }
}
