package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class JwtService extends BaseTest {

    public String token;

    @BeforeMethod
    public void setup(){
        setupProps();
        util.RestAssuredUtil.setBaseURI(prop.getProperty("jwt_url"));
        util.RestAssuredUtil.setBasePath("");
    }

    @Test(priority = 1)
    public void PostLogin() throws IOException {
        System.out.println("PostLogin");
        String jsonBody = testUtil.generateStringFromResource("./src/main/resources/testdata/jwt/loginNo2fa.json");
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(jsonBody);
        Response response = request.log().all().post("/v1/auth/jwt-auth/");
        testUtil.checkStatusIs200(response);
        token = response.getBody().jsonPath().getString("token");
        System.out.println(response.getBody().asString());
    }

    @Test(priority = 2)
    public void RefreshToken(){
        System.out.println("RefreshToken");
        JSONObject jsonbody = new JSONObject();
        jsonbody.put("token", "JWT " + token);
        Response response = given()
                .when().log().all()
                .header("Content-Type", "application/json")
                .header("authorization", "JWT "+token)
                .header("accept", "application/json")
                .body(jsonbody.toString())
                .post("/v1/auth/jwt-refresh/")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    @Test(priority = 3)
    public void PostLogout(){
        Response response = given()
                .header("authorization", "JWT "+token)
                .when().log().all()
                .post("/v1/auth/jwt-logout/")
                .then()
                .extract().response();
        Assertions.assertEquals(204, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    @Test
    public void PostLoginIncorrectCredentials() throws IOException {
        String jsonBody = testUtil.generateStringFromResource("./src/main/resources/testdata/jwt/incorrectlogin.json");
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(jsonBody);
        Response response = request.log().all().post("/v1/auth/jwt-auth/");
        Assertions.assertEquals(401, response.statusCode());
        System.out.println(response.getBody().asString());
    }

    @Test(priority = 4)
    public void PostLoginYes2fa() throws IOException{
        System.out.println("PostLoginYes2fa");
        String jsonBody = testUtil.generateStringFromResource("./src/main/resources/testdata/jwt/loginYes2fa.json");
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(jsonBody);
        Response response = request.log().all().post("/v1/auth/jwt-auth/");
        System.out.println(response.getBody().asString());
        testUtil.checkStatusIs200(response);
        token = response.getBody().jsonPath().getString("token");
    }


    @Test(priority = 5)
    public void PostLoginWith2fa() throws Exception {
        System.out.println("PostLoginWith2fa");
        JSONObject jsonBody = util.Utils.parseJSONFile("./src/main/resources/testdata/jwt/loginYes2fa.json");
        TimeUnit.SECONDS.sleep(10);
        String code = util.GmailQuickstart.get2FACode();
        jsonBody.put("mfa_token",code);
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(jsonBody.toString());
        Response response = request.log().all().post("/v1/auth/jwt-auth/");
        System.out.println(response.getBody().asString());
        testUtil.checkStatusIs200(response);
        token = response.getBody().jsonPath().getString("token");
        PostLogout();
    }

    public void main() throws Exception {
        setup();
        PostLogin();
        RefreshToken();
        PostLogout();
        PostLoginIncorrectCredentials();
        PostLoginYes2fa();
        PostLoginWith2fa();

    }
}

