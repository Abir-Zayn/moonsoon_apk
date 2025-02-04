package com.example.moonsoon.ui.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moonsoon.data.entity.WeatherResponse
import com.example.moonsoon.data.repository.WeatherRepository
import com.example.moonsoon.data.resource.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


//HiltViewModel are part of architecture components library that helps to manage UI
//related data in a lifecycle conscious way. using HiltViewModel, you can easily inject dependencies into your ViewModel without having to
//manually create instances .For eg HiltViewModel will be responsible of UI changes of city.
@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepo: WeatherRepository) :
    ViewModel() {
    private var _weatherState =
        MutableStateFlow<ResourceState<WeatherResponse>>(ResourceState.Loading())
    var weatherState: StateFlow<ResourceState<WeatherResponse>> = _weatherState.asStateFlow()

    val _cityName =
        MutableStateFlow<String>("Dhaka") //By default, we are showing the weather of Dhaka
    val cityName: StateFlow<String> = _cityName.asStateFlow()

    init {
        getWeather()
    }


    fun getWeather() {
        viewModelScope.launch {
            weatherRepo.getWeather(cityName.value).collectLatest {
                _weatherState.value = it
            }
        }
    }

    fun setCityName(cityName: String) {
        _cityName.value = cityName
        getWeather()
    }
}