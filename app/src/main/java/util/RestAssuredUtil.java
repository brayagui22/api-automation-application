package util;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredUtil {
    //Sets Base URI
    public static void setBaseURI(String s) {
        RestAssured.baseURI = s;
    }

    //Sets base path
    public static void setBasePath(String basePathTerm) {
        RestAssured.basePath = basePathTerm;
    }

    //Reset Base URI (after test)
    public static void resetBaseURI() {
        RestAssured.baseURI = null;
    }

    //Reset base path
    public static void resetBasePath() {
        RestAssured.basePath = null;
    }

    //Sets ContentType
    public static void setContentType() {
        RestAssured.given().header("Content-Type", "application/json");
    }

    //set request body
    public static void setRequestBody(String s){
        RestAssured.given().body(s);
    }

    //Returns response by given path
    public static Response getResponse(String path) {
        return given().log().all().get(path);
    }

    //Returns response
    public static Response getResponse() {
        return given().get();
    }

    //Returns JsonPath object
    public static JsonPath getJsonPath(Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }

    //Returns post
    public static Response post(){
        return given().log().all().post();
    }

    //Returns post
    public static Response post(String path){
        return given().post(path);
    }

}