package com.example.feather.ui.theme.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val baseURL = "https://api.weatherapi.com"
    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    // this is the class instance of WeatherApi, through this we will access all the function and properties of the interface WeatherApi
    val weatherApi : WeatherApi = getInstance().create(WeatherApi::class.java)
}