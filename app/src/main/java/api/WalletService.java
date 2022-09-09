package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class WalletService extends BaseTest{

    public void main() throws IOException {
        setup();
        PostLogin();
       // GetUsers();
       // GetAccount();
       // GetTransactions();
       // GetJurisdictions();

    }

    public String userId;
    public String uuId;

    public void setup(){
        setupProps();
        util.RestAssuredUtil.setBaseURI(prop.getProperty("wallet_url"));
        util.RestAssuredUtil.setBasePath("");
    }

    public void PostLogin() throws IOException {
        util.RestAssuredUtil.setBaseURI(prop.getProperty("jwt_url"));
        String jsonBody = testUtil.generateStringFromResource("./src/main/resources/testdata/jwt/loginNo2fa.json");
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(jsonBody);
        Response response = request.log().all().post("/v1/auth/jwt-auth/");
        testUtil.checkStatusIs200(response);
        //set variables for future get calls
        token = response.getBody().jsonPath().getString("token");
        userId = response.getBody().jsonPath().getString("user_id");
        //reset URI to wallet after posting login
        System.out.println(response.getBody().asString());
    }

    /*
    @Test
    public void GetUsers(){
        util.RestAssuredUtil.setBaseURI(prop.getProperty("wallet_url"));
        Response response = given()
                .header("authorization", "JWT " + token)
                .when().log().all()
                .get("/api/v1/users/"+userId+"/")
                .then()
                .extract().response();
        //set uuID
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
        uuId = response.getBody().jsonPath().getString("data.attributes.guid");

    }

    @Test
    public void GetAccount(){
        Response response = given()
                .header("authorization","JWT " + token)
                .when().log().all()
                .get("/api/v1/account/"+userId+"/")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }



    public void GetTransactions(){
        Response response = given()
                .header("authorization","JWT " + token)
                .when().log().all()
                .get("/api/v1/transactions/")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }



    public void GetJurisdictions(){
        Response response = given()
                .header("authorization","JWT " + token)
                .when().log().all()
                .get("/api/v1/jurisdictions/1/")
                .then()
                .extract().response();
        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.getBody().asString());
    }

     */
}

