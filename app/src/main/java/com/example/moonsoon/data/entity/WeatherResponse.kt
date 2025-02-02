package com.example.moonsoon.data.entity

data class WeatherResponse(
    val name:String,
    val main:Main,
    val weather:List<Weather>,
    val wind:Wind,
)

data class Main(
    val temp:Double,
    val tempMin:Double,
    val tempMax:Double,
    val humidity:Int,
    val pressure: Double,
    val feelsLike : Double
)

data class Weather (
    val main:String,
    val description:String,
    val icon:String,
    val id: String
)

data class Wind(
    val speed:Double,
)