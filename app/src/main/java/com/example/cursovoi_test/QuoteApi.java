package com.example.cursovoi_test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuoteApi {
    @GET("/api/1.0/")
    Call<ForismaticQuoteResponse> getQuote(@Query("method") String method, @Query("format") String format, @Query("key") String key);
}


