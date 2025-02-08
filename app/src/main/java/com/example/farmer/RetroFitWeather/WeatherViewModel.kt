package com.example.farmer.RetroFitWeather

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feather.ui.theme.api.Constants
import com.example.feather.ui.theme.api.NetworkResponse
import com.example.feather.ui.theme.api.RetrofitInstance
import com.example.feather.ui.theme.api.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

//    var _cityName by mutableStateOf("london")

    private val _cityName = MutableStateFlow("jalandhar")
    val cityName: StateFlow<String> = _cityName

    fun updateText(newText: String) {
        _cityName.value = newText
    }
    fun getData(city : String){
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            val response = weatherApi.getWeather(apiKey = Constants.apiKey, city = city)
            try{
                if (response.isSuccessful){
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data...")
                }
            }catch (e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data...")
            }
        }
    }
}