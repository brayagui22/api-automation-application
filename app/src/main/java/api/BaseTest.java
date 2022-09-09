package api;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import util.TestUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    public Properties prop = new Properties();
    public Response res = null; //Response
    public JsonPath jp  = null; //JsonPath
    public String token;


    //Instantiate a Helper Test Methods (testUtils) Object
    TestUtil testUtil = new TestUtil();


    @BeforeClass
    public void setup() {
        //Test Setup
        util.RestAssuredUtil.setBaseURI(""); //Setup Base URI
        util.RestAssuredUtil.setBasePath("api"); //Setup Base Path
        util.RestAssuredUtil.setContentType(); //Setup Content Type

    }

    public void setupProps() {
        try (InputStream input = new FileInputStream("./src/main/resources/env/env.dev")) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    @AfterClass
    public void afterTest() {
        //Reset Values
        util.RestAssuredUtil.resetBaseURI();
        util.RestAssuredUtil.resetBasePath();
    }
}