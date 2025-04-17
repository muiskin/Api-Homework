package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import stepdefinitions.End2EndTestStepDefinitions;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static stepdefinitions.End2EndTestStepDefinitions.email;

public class Authorization {

    public static String token(){

        Map payload = new HashMap<>();
        payload.put("email",email);
        payload.put("password","Password.123");

        Response response = given().contentType(ContentType.JSON).body(payload).post("https://thinking-tester-contact-list.herokuapp.com/users/login");

        return response.jsonPath().getString("token");
    }

}
