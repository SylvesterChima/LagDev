package com.example.sylvester.lagdev.remote;


import com.example.sylvester.lagdev.model.mDevs;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sylvester on 28/08/2017.
 */

public interface APIService {
    //gethub api
    @GET("/search/users?q=language:java+location:lagos")
    Call<mDevs> getDevelopers();
}
