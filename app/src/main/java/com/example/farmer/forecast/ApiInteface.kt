package com.example.farmer.forecast

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInteface {
    @GET("/v1/forecast.json")
    suspend fun getForecast(
        @Query("key") api_key :String,
        @Query ("q") city :String,
        @Query("days") days:Int
    ):ForecastResult
}