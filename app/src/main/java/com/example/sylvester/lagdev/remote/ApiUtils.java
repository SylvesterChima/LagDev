package com.example.sylvester.lagdev.remote;

/**
 * Created by Sylvester on 28/08/2017.
 */

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "https://api.github.com/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
