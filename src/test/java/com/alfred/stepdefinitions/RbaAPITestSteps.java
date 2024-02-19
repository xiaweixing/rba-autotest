package com.alfred.stepdefinitions;

import com.alfred.common.Hooks;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;

public class RbaAPITestSteps {

    private static final String BASE_URL = Hooks.getBaseUrlApi();
    private static RequestSpecification request;
    private static Response response;

    @Given("user create a PET")
    public void userCreateAPET(DataTable dataTable) {
        //System.out.println("BASE_URL = " + BASE_URL);
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type",  "application/json");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String petID = row.get("ID");
            String petName = row.get("Name");
            String expectedResponseCode = row.get("ExpectedHttpResponseCode");

            JSONObject requestParams = new JSONObject();
            requestParams.put("id", petID);
            requestParams.put("name", petName);
            request.body(requestParams.toString());
//            System.out.print("------> Request JSON Body: " + request.log().body());

//            System.out.println("############# POST");
            response = request.post("/pet");
//            System.out.println("\nStatus Code: " + response.getStatusCode());
            assertEquals("Unexpected response code!", expectedResponseCode, String.valueOf(response.getStatusCode()));
//            System.out.println("------> Response JSON Body: " + response.getBody().prettyPrint());
//            System.out.println(response.jsonPath().getString("id"));
//            System.out.println(response.jsonPath().getString("name"));
            assertEquals("Unexpected pet id!", petID, response.jsonPath().getString("id"));
            assertEquals("Unexpected pet name!", petName, response.jsonPath().getString("name"));
            System.out.println();
        }
    }

    @And("user get the PET")
    public void userGetThePET(DataTable dataTable) {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type",  "application/json");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String petID = row.get("ID");
            String petName = row.get("Name");
            String expectedResponseCode = row.get("ExpectedHttpResponseCode");

//            System.out.println("############# GET");
            response = request.get("/pet/" + petID);
//            System.out.println("\nStatus Code: " + response.getStatusCode());
            assertEquals("Unexpected response code!", expectedResponseCode, String.valueOf(response.getStatusCode()));
//            System.out.println("------> Response JSON Body: " + response.getBody().prettyPrint());
//            System.out.println(response.jsonPath().getString("id"));
//            System.out.println(response.jsonPath().getString("name"));
            assertEquals("Unexpected pet id!", petID, response.jsonPath().getString("id"));
            assertEquals("Unexpected pet name!", petName, response.jsonPath().getString("name"));
            System.out.println();
        }
    }

    @Then("user update the PET with a new name")
    public void userUpdateThePETWithANewName(DataTable dataTable) {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type",  "application/json");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String petID = row.get("ID");
            String petName = row.get("Name");
            String expectedResponseCode = row.get("ExpectedHttpResponseCode");

            JSONObject requestParams = new JSONObject();
            requestParams.put("id", petID);
            requestParams.put("name", petName);
            request.body(requestParams.toString());
//            System.out.print("------> Request JSON Body: " + request.log().body());

//            System.out.println("############# PUT");
            response = request.put("/pet");
//            System.out.println("\nStatus Code: " + response.getStatusCode());
            assertEquals("Unexpected response code!", expectedResponseCode, String.valueOf(response.getStatusCode()));
//            System.out.println("------> Response JSON Body: " + response.getBody().prettyPrint());
//            System.out.println(response.jsonPath().getString("id"));
//            System.out.println(response.jsonPath().getString("name"));
            assertEquals("Unexpected pet id!", petID, response.jsonPath().getString("id"));
            assertEquals("Unexpected pet name!", petName, response.jsonPath().getString("name"));
            System.out.println();
        }
    }

    @Then("user delete the PET")
    public void userDeleteThePET(DataTable dataTable) {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type",  "application/json");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String petID = row.get("ID");
            String expectedResponseCode = row.get("ExpectedHttpResponseCode");

//            System.out.println("############# DELETE");
            response = request.delete("/pet/" + petID);
//            System.out.println("\nStatus Code: " + response.getStatusCode());
            assertEquals("Unexpected response code!", expectedResponseCode, String.valueOf(response.getStatusCode()));
//            System.out.println("------> Response JSON Body: " + response.getBody().prettyPrint());
//            System.out.println(response.jsonPath().getString("message"));
            assertEquals("Unexpected message!", petID, response.jsonPath().getString("message"));
            System.out.println();
        }
    }

    @And("verify PET is deleted")
    public void verifyPETIsDeleted(DataTable dataTable) {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type",  "application/json");

        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : data) {
            String petID = row.get("ID");
            String message = row.get("Message");
            String expectedResponseCode = row.get("ExpectedHttpResponseCode");

//            System.out.println("############# GET");
            response = request.get("/pet/" + petID);
//            System.out.println("\nStatus Code: " + response.getStatusCode());
            assertEquals("Unexpected response code!", expectedResponseCode, String.valueOf(response.getStatusCode()));
//            System.out.println("------> Response JSON Body: " + response.getBody().prettyPrint());
//            System.out.println(response.jsonPath().getString("message"));
            assertEquals("Unexpected message!", message, response.jsonPath().getString("message"));
            System.out.println();
        }
    }
}
