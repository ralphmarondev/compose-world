package com.ralphmarondev.weather.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.weather.API_KEY
import com.ralphmarondev.weather.data.remote.NetworkResponse
import com.ralphmarondev.weather.data.remote.RetrofitInstance
import com.ralphmarondev.weather.data.remote.WeatherModel
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherResult: LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String) {
        _weatherResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(
                    apiKey = API_KEY,
                    city = city
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                        Log.d("HomeViewModel", "Response: body: ${response.body()}, code: ${response.code()}")
                    }
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
                Log.e("HomeViewModel", "Error: ${e.message}")
            }
        }
    }
}