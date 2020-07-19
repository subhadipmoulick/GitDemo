package stepDefinition;

import Resources.APIResouces;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class stepDefinition extends Utils {
    static RequestSpecification res;
    static Response response;
    static String place_id;
    TestDataBuild data= new TestDataBuild();
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_Place_Payload_with(String name,String language,String address) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        res =given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resources, String method) {
        // Write code here that turns the phrase above into concrete actions
        ResponseSpecification re =new ResponseSpecBuilder().expectContentType("application/json").expectStatusCode(200).build();
        APIResouces apiResource = APIResouces.valueOf(resources);
        System.out.println(apiResource.getAPIResources());
        if (method.equalsIgnoreCase("POST"))
             response =res.when().post(apiResource.getAPIResources()).then().spec(re).extract().response();
        else if(method.equalsIgnoreCase("GET"))
            response =res.when().post(apiResource.getAPIResources()).then().spec(re).extract().response();

        //String output = response.asString();
        //System.out.println(output);
    }

    @Then("The API is sucessfully called with status code {int}")
    public void the_API_is_sucessfully_called_with_status_code(Integer int1) {

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String expectedValue) {
        // Write code here that turns the phrase above into concrete actions

        Assert.assertEquals(getJsonPath(response,keyValue),expectedValue);

    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String apiName) throws IOException {

        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(apiName,"GET");
        String actualName = getJsonPath(response,"name");
        Assert.assertEquals(actualName,expectedName);
    }

    @Given("DeletePlace Payload")
    public void deleteplace_Payload() throws IOException {


       res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

}
