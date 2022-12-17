package com.example.funkyweather.network

class NetworkWeatherDataSource(private val weatherApiService: WeatherApiService) :
    RemoteWeatherDataSource {

    override suspend fun getWeatherList() = weatherApiService.getList()
}