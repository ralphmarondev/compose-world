package com.ralphmarondev.weather.data.remote

import com.ralphmarondev.weather.domain.model.Current
import com.ralphmarondev.weather.domain.model.Location
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherModel(
    val current: Current,
    val location: Location
)

interface WeatherApi {
    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ): Response<WeatherModel>
}