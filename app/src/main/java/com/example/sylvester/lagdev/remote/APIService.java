package com.example.sylvester.lagdev.remote;


import com.example.sylvester.lagdev.model.mDevs;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Sylvester on 28/08/2017.
 */

public interface APIService {
    //gethub api
    @GET("/search/users")
    Call<mDevs> getDevs(
            @Query("q") String q,
            @Query("per_page") int per_page,
            @Query("page") int page
    );
}
