package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class CampaignService extends BaseTest{

    public void main() throws IOException {
        setup();
        GetBanners();
        PostBanner();
        GetBannerId();
        PutBanner();
        PatchSuspendBanner();
        PatchUnsuspendBanner();
        DeleteBanner();
        GetCampaigns();
    }

    private String bannerId;

    public void setup(){
        setupProps();
        util.RestAssuredUtil.setBaseURI(prop.getProperty("campaign_url"));
        util.RestAssuredUtil.setBasePath("");
    }

    public void GetBanners () {
        res = util.RestAssuredUtil.getResponse("/api/v1/banners/all");
        testUtil.checkStatusIs200(res);
        System.out.println(res.getBody().asString());
    }

    public void PostBanner() throws IOException{
        String jsonBody = testUtil.generateStringFromResource("./src/main/resources/testdata/campaign/postBanner.json");
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(jsonBody);
        Response response = request.log().all().post("/api/v1/banners/");
        Assertions.assertEquals(201, response.statusCode());
        bannerId = response.getBody().jsonPath().getString("bannerId");
        System.out.println(response.getBody().asString());
    }

    public void GetBannerId(){
        res = util.RestAssuredUtil.getResponse("/api/v1/banners/"+bannerId);
        testUtil.checkStatusIs200(res);
        System.out.println(res.getBody().asString());
    }

    public void PutBanner() throws IOException{
        String jsonBody = testUtil.generateStringFromResource("./src/main/resources/testdata/campaign/putBanner.json");
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(jsonBody);
        Response response = request.log().all().put("/api/v1/banners/"+bannerId);
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals(response.getBody().jsonPath().getString("functionalType") , "REFEREE");
        System.out.println(response.getBody().asString());
    }

    public void DeleteBanner(){
        RequestSpecification request = given();
        Response response = request.log().all().delete("/api/v1/banners/"+bannerId);
        Assertions.assertEquals(204, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void PatchSuspendBanner(){
        RequestSpecification request = given();
        Response response = request.log().all().patch("/api/v1/banners/suspend/"+bannerId);
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void PatchUnsuspendBanner(){
        RequestSpecification request = given();
        Response response = request.log().all().patch("/api/v1/banners/unsuspend/"+bannerId);
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void GetCampaigns () {
        res = util.RestAssuredUtil.getResponse("/api/v1/campaigns");
        testUtil.checkStatusIs200(res);
        System.out.println(res.getBody().asString());
    }


    /*
    Waiting to add these until we get a DELETE campaign
    @Test
    public void PostCampaign(){

    }

    @Test
    public void PutCampaign(){

    }

    @Test
    public void PatchSuspendCampaign(){

    }

    @Test
    public void PatchUnsuspendCampaign(){

    }

     */

}

