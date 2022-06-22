package io.axelixx.api.steps;

import io.axelixx.api.utils.DataStore;
import io.axelixx.api.utils.EnvironmentPropertiesProvider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class StravaSteps {

    String clientId = EnvironmentPropertiesProvider.INSTANCE.getStravaClientId();
    String clientSecret = EnvironmentPropertiesProvider.INSTANCE.getStravaClientSecret();
    String refresh_token = EnvironmentPropertiesProvider.INSTANCE.getStravaRefreshToken();

    @Given("^I have my Strava API access token$")
    public void retrieveStravaAccessToken() {

        DataStore.INSTANCE.setAccessToken(given().
                param("client_id", "37429").
                param("client_secret", clientSecret).
                param("refresh_token", refresh_token).
                param("grant_type", "refresh_token").
                when().
                post("https://www.strava.com/oauth/token").
                then().
                statusCode(200).
                extract().
                path("access_token"));
    }

    @When("^I retrieve the (.*) stats$")
    public void retrieveStats(String userId) {
        DataStore.INSTANCE.setUserStatsResponse(given().
                header("Authorization", "Bearer " + DataStore.INSTANCE.getAccessToken()).
                when().
                get("https://www.strava.com/api/v3/athletes/" + userId + "/stats").
                then().
                contentType(ContentType.JSON).
                extract().
                response());
        System.out.println(DataStore.INSTANCE.getUserStatsResponse().asPrettyString());
    }

    @Then("^I receive a (.*) response code$")
    public void validateResponseCode(String responseCode) {
        assertThat(Integer.parseInt(responseCode)).isEqualTo(DataStore.INSTANCE.getUserStatsResponse().getStatusCode());
    }

    @Then("^I get the user all-time stats$")
    public void validateAllTimeStats() {
        System.out.println("Then Statment 2");
    }
}
