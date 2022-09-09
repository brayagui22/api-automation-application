package api;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class RegistrationService extends BaseTest{


    public String emailbody;
    public String registrationUrl;
    public URL url;
    public String code;

    public void main() throws Exception {
        setup();
        GetStates();
        checkUser();
    }

    public void setup(){
        setupProps();
        util.RestAssuredUtil.setBaseURI(prop.getProperty("registration_url"));
        util.RestAssuredUtil.setBasePath("");
    }

    public void GetStates(){
        Response response = given()
                .when().log().all()
                .get("/register/v1/location/states/US")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    public void checkUser() throws Exception {
        Response response = given()
                .header("authorization", prop.getProperty("env_token"))
                .when().log().all()
                .get("/v1/cred/credential/"+ Utils.registrationEmail)
                .then()
                .extract().response();
        Assertions.assertEquals(404, response.statusCode());
        System.out.println(response.getBody().asString());
    }
}
