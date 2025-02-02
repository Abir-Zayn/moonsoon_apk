package com.example.moonsoon.data.api


import com.example.moonsoon.BuildConfig
import com.example.moonsoon.data.entity.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("weather")

    suspend fun  getWeather(
        @Query("q") CityName: String,
        @Query("lon") units: String  ="metric",
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): WeatherResponse

}