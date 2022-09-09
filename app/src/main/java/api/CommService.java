package api;


public class CommService extends BaseTest  {

    public void setup(){
        setupProps();
        util.RestAssuredUtil.setBaseURI(prop.getProperty("comm_url"));
        util.RestAssuredUtil.setBasePath("");
    }

    public void HealthCheck() {
        res = util.RestAssuredUtil.getResponse("/actuator/health");
        testUtil.checkStatusIs200(res);
        System.out.println(res.getBody().asString());
    }

    public void main(){
        setup();
        HealthCheck();
    }
}

