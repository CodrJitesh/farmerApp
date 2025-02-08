package com.example.farmer.forecast
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
class ForecastViewModel :ViewModel() {


    var weather by mutableStateOf<ForecastResult?>(null)

    fun fetchWeather(city: String, days: Int) {
        val api_key = "d2ae2624e1ad4bc391a111147240110"
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getForecast(api_key, city, days)
                weather = response
                Log.d("mytag", "no error")


            } catch (e: Exception) {
                // Handle error
                Log.d("mytag", "error occ.${e}")
            }
        }
    }
}