package com.example.farmer.forecast

data class ForecastResult(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)