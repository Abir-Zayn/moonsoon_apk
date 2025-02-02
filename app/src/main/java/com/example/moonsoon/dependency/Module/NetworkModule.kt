package com.example.moonsoon.dependency.Module

import com.example.moonsoon.data.api.WeatherAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherAPI {
        return retrofit.create(WeatherAPI::class.java)
    }
}

//Retrofit is a type-safe HTTP client for Android and Java. It makes it relatively easy to retrieve and upload JSON (or other structured data) via
// a REST-based web service. In Retrofit you configure which converter is used for the data serialization.
// Typically for JSON you use Gson, but you can add custom converters to process XML or other protocols.

//Singleton component/annotation =>
// A singleton is a class that allows only a single instance [Dont have 2nd copy of the class].
