package com.example.funkyweather.repository

import com.example.funkyweather.network.RemoteWeatherDataSource

class WeatherRepositoryImpl(private val weatherDataSource: RemoteWeatherDataSource) :
    WeatherRepository {
    override suspend fun getWeatherItems() = weatherDataSource.getWeatherList()
}