package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import jdk.jshell.execution.Util;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.json.*;
import org.testng.internal.BaseTestMethod;

import static io.restassured.RestAssured.given;


public class PricingService extends BaseTest {

    public void main() {
        setup();
        GetPricingHistoryYear();
        GetPricingHistoryMonth();
        GetPricingHistoryDay();
        GetSpotPriceSell();
        GetSpotPriceBuy();
        GetQuoteBuyUSD();
        GetQuoteBuyGHS();
        GetQuoteSellUSD();
        GetQuoteSellGHS();
    }

    public void setup(){
        setupProps();
        util.RestAssuredUtil.setBaseURI(prop.getProperty("pricing_url"));
        util.RestAssuredUtil.setBasePath("");
        token = prop.getProperty("env_token");
    }

    public void GetPricingHistoryYear(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("period", "year");
        requestParams.put("currency", "USD");

        Response response = given()
                .header("authorization", token)
                .when().log().all()
                .body(requestParams.toString())
                .get("/api/v1/history/")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void GetPricingHistoryMonth(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("period", "month");
        requestParams.put("currency", "USD");

        Response response = given()
                .header("authorization", token)
                .when().log().all()
                .body(requestParams.toString())
                .get("/api/v1/history/")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void GetPricingHistoryDay(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("period", "day");
        requestParams.put("currency", "USD");

        Response response = given()
                .header("authorization", token)
                .when().log().all()
                .body(requestParams.toString())
                .get("/api/v1/history/")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void GetSpotPriceSell(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("period", "day");
        requestParams.put("currency", "USD");

        Response response = given()
                .header("authorization", token)
                .when().log().all()
                .queryParam("method","ACH")
                .queryParam("customerTier", "7")
                .queryParam("xgcAmount", "383114177")
                .get("/api/v1/spotprice/xgc/SELL/USD/")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void GetSpotPriceBuy(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("period", "day");
        requestParams.put("currency", "USD");

        Response response = given()
                .header("authorization", token)
                .when().log().all()
                .queryParam("method","ACH")
                .queryParam("customerTier", "7")
                .queryParam("xgcAmount", "383114177")
                .get("/api/v1/spotprice/xgc/BUY/USD/")
                .then()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void GetQuoteBuyUSD(){
        Response response = given()
                .header("authorization", token).log().all()
                .when()
                .queryParam("method","ACH")
                .queryParam("customerTier", "7")
                .queryParam("fiatAmount", "777")
                .get("/api/v1/quote/fiat/"+5280+"/BUY/USD")
                .then()
                .extract().response();

        System.out.println(response.asString());
        Assertions.assertEquals(200, response.statusCode());
    }

    public void GetQuoteSellUSD(){
        Response response = given()
                .header("authorization", token).log().all()
                .when()
                .queryParam("method","ACH")
                .queryParam("customerTier", "7")
                .queryParam("fiatAmount", "777")
                .get("/api/v1/quote/fiat/"+5280+"/SELL/USD")
                .then()
                .extract().response();

        System.out.println(response.asString());
        Assertions.assertEquals(200, response.statusCode());
    }

    public void GetQuoteBuyGHS(){
        Response response = given()
                .header("authorization", token).log().all()
                .when()
                .queryParam("method","ACH")
                .queryParam("customerTier", "7")
                .queryParam("fiatAmount", "777")
                .get("/api/v1/quote/fiat/"+5280+"/BUY/GHS")
                .then()
                .extract().response();

        System.out.println(response.asString());
        Assertions.assertEquals(200, response.statusCode());
    }

    public void GetQuoteSellGHS(){
        Response response = given()
                .header("authorization", token).log().all()
                .when()
                .queryParam("method","ACH")
                .queryParam("customerTier", "7")
                .queryParam("fiatAmount", "777")
                .get("/api/v1/quote/fiat/"+5280+"/SELL/GHS")
                .then()
                .extract().response();

        System.out.println(response.asString());
        Assertions.assertEquals(200, response.statusCode());
    }

}
