package stepdefinitions;

import utils.ApiClient;
import com.google.gson.JsonObject;
import io.qameta.allure.Allure;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApiValidationSteps {
    private JsonObject response;

    @When("I send a GET request to URL")
    public void iSendAGETRequestTo() {
        response = ApiClient.sendGetRequest();
    }

    @Then("the response contains the key {string} with value {string}")
    public void theResponseContainsTheKeyWithValue(String key, String expectedValue) {
        try {
            assertEquals(expectedValue, response.get(key).getAsString(), "API response validation failed");
            Allure.addAttachment("Expected Value", expectedValue);
            Allure.addAttachment("Actual Value", response.get(key).getAsString());
        } catch (AssertionError e) {
            Allure.addAttachment("Expected Value", expectedValue);
            Allure.addAttachment("Actual Value", response.get(key).getAsString());
            throw e;
        }
    }
}