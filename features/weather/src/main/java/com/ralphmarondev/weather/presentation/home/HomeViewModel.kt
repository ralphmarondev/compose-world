package com.ralphmarondev.weather.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.weather.API_KEY
import com.ralphmarondev.weather.data.remote.NetworkResponse
import com.ralphmarondev.weather.data.remote.RetrofitInstance
import com.ralphmarondev.weather.data.remote.WeatherModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val weatherApi = RetrofitInstance.weatherApi
    private val _weatherResult = MutableStateFlow<NetworkResponse<WeatherModel>>(
        NetworkResponse.Loading
    )
    val weatherResult = _weatherResult.asStateFlow()

    private val _location = MutableStateFlow("London")
    val location: StateFlow<String> get() = _location

    init {
        getData()
    }

    private fun getData() {
        _weatherResult.value = NetworkResponse.Loading

        viewModelScope.launch {
            try {
                val response = weatherApi.getWeather(
                    apiKey = API_KEY,
                    city = _location.value.trim()
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                        Log.d(
                            "HomeViewModel",
                            "Response: body: ${response.body()}, code: ${response.code()}"
                        )
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

    fun onLocationChange(value: String) {
        _location.value = value
    }

    fun onSearch(
        response: (Boolean, String) -> Unit
    ) {
        if (_location.value.trim().isBlank()) {
            response(false, "Location cannot be empty")
            return
        }
        getData()
    }
}