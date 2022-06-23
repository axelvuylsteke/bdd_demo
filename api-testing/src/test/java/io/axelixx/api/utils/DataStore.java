package io.axelixx.api.utils;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

public class DataStore {
    private DataStore() {
    }

    public static DataStore INSTANCE = new DataStore();

    public static void resetDataStore() {
        INSTANCE = new DataStore();
    }

    private @Getter @Setter String accessToken;
    private @Getter @Setter
    Response userStatsResponse;
    private @Getter @Setter Response loggedInAthleteDataResponse;
}
