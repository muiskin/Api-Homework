package stepdefinitions;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pages.CLHomePage;
import pages.CLSignUpPage;
import pojos.UserPojo;
import utilities.Driver;
import utilities.ObjectMapperUtils;

import static base_urls.ContactListBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class End2EndTestStepDefinitions {

    private CLSignUpPage clSignUpPage;
    String firstname;
    String lastname;
    public static String email;
    UserPojo expectedData;
    Response response;

    @Given("user goes to {string}")
    public void user_goes_to(String url) {
        Driver.getDriver().get(url);

    }
    @When("user clicks on sign up button")
    public void user_clicks_on_sign_up_button() {
        CLHomePage clHomePage = new CLHomePage();
        clHomePage.signup.click();
    }
    @When("User enters firstname, lastname, email, password")
    public void user_enters_firstname_lastname_email_password() {

        Faker faker = new Faker();
        firstname = faker.name().firstName();
        lastname = faker.name().lastName();
        email = faker.internet().emailAddress();

        clSignUpPage = new CLSignUpPage();
        clSignUpPage.firstname.sendKeys(firstname);
        clSignUpPage.lastname.sendKeys(lastname);
        clSignUpPage.email.sendKeys(email);
        clSignUpPage.password.sendKeys("Password.123");

    }
    @When("user clicks on submit button")
    public void user_clicks_on_submit_button() {

        clSignUpPage = new CLSignUpPage();
        clSignUpPage.submit.click();

    }
    @When("user closes browser")
    public void user_closes_browser() {
        Driver.closeDriver();
    }
    @Then("validate the user via API request")
    public void validate_the_user_via_api_request() {

        spec.pathParams("first","users","second","me");

        Response response = given(spec).contentType(ContentType.JSON).get("{first}/{second}");
        response.prettyPrint();
        response.then().statusCode(200).body("firstName", equalTo(firstname),"lastName",equalTo(lastname),"email",equalTo(email));

    }

    @Given("set the url for update")
    public void setTheUrlForUpdate() {
        spec.pathParams("first","users","second","me");
    }

    @And("set the expected data for update")
    public void setTheExpectedDataForUpdate() {
        Faker faker = new Faker();
        firstname = faker.name().firstName();
        lastname = faker.name().lastName();
        email = faker.internet().emailAddress();

        expectedData = new UserPojo(firstname,lastname,"Password.123",email);
        System.out.println("expectedData UPDATE = " + expectedData);

    }

    @When("send the patch request for update")
    public void sendThePatchRequestForUpdate() {
        response = given(spec).body(expectedData).contentType(ContentType.JSON).patch("{first}/{second}");
        response.prettyPrint();
    }

    @Then("do assertion for update")
    public void doAssertionForUpdate() {
        UserPojo actualData = response.as(UserPojo.class);
        assertEquals(200, response.statusCode());
        assertEquals(actualData.getFirstName(),expectedData.getFirstName());
        assertEquals(actualData.getLastName(),expectedData.getLastName());
        assertEquals(actualData.getEmail(),expectedData.getEmail());
    }

    @Given("set the url for delete")
    public void setTheUrlForDelete() {
        spec.pathParams("first","users","second","me");
    }

    @When("send the delete request for delete")
    public void sendTheDeleteRequestForDelete() {
        response = given(spec).contentType(ContentType.JSON).delete("{first}/{second}");
        response.prettyPrint();
    }

    @Then("do assertion for delete")
    public void doAssertionForDelete() {
        assertEquals(200, response.statusCode());
        assert response.asString().isEmpty();
    }
}
