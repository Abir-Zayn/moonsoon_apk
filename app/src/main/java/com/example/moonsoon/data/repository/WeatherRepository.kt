package com.example.moonsoon.data.repository

import com.example.moonsoon.data.api.WeatherAPI
import com.example.moonsoon.data.entity.WeatherResponse
import com.example.moonsoon.data.resource.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


// interact with the weatherAPI to fetch the weather data.
class WeatherRepository  @Inject constructor(private val weatherAPI: WeatherAPI) {

    //get the weather data for a given city name
    suspend fun getWeather(cityName: String) : Flow<ResourceState<WeatherResponse>> = flow {
        emit(ResourceState.Loading()) //data loading
        try {
            val response: WeatherResponse = weatherAPI.getWeather(cityName)
            emit(ResourceState.Success(response))  //success
        } catch (e: Exception) {
            emit(ResourceState.Error(e.message ?: "Unknown Error" )) //error
        }
    }

}