package util;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static String registration_url="http://registration.dev.gmint.io";
    public static String pricing_url="http://pricing.dev.gmint.io";
    public static String campaign_url="http://campaign.dev.gmint.io";
    public static String jwt_url="http://jwt.dev.gmint.io";
    public static String wallet_url="http://wallet.dev.gmint.io";
    public static String env_token="JWT eyJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE1NDc1MDU0ODMsImV4cCI6OTU0NzE2NDA5MiwidXNlcl9pZCI6MCwidXNlcm5hbWUiOiJqd3RfdXNlciIsInVzZXJfdHlwZSI6InN5c3RlbSJ9.KO8Z9UXrgPJAgbtsnmV8EqPVD9Keh2bAXGHunaMbHaRB_StBKfGuOYq4T0H4WZuCiA8t4FiI-99iku7xV1GbTieJ1_pwduRnCoI8_m2ZbM2UZD7Ohjl_wNizta5GUIVhd0kmtAAVSEAJahNf6X-qkOGNwrNt27MAviNVhu6zNg-6VBl70JYSg1VkRLkkuruiNYil467-XKum0iBY7aU_doW281Q9j8Uyuii7xYUJ2SbBx4nmd0K9kJBYoarej4fk9LREJPyyeuyzVzM4h_9bWb0jrjPrm0GdnRzsjJQtiUu4IgtZtQqFWQCI_pEXj5FI7lfcW0Ec3lIagbO0QcP6_A";
    public static String email = "brayaguiqa@gmail.com";
    public static String registrationEmail = "brayaguiqa+"+RandomStringUtils.randomAlphanumeric(5)+"@gmail.com";
    public static String payment_url="http://payment.dev.gmint.io";
    public static String regFile = "src/test/java/registration.json";
    public static String postOrderFile = "src/test/java/postOrder.json";

    public static JSONObject parseJSONFile(String filename) throws JSONException, IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);
    }

}
