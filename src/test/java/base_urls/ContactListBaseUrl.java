package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utilities.Authorization.token;

public class ContactListBaseUrl {

    public static RequestSpecification spec;

    static {

        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com/")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer "+token())
                .build();


    }
}
