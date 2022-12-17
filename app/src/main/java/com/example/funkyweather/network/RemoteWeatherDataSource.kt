package com.example.funkyweather.network

import retrofit2.Response

interface RemoteWeatherDataSource {
    suspend fun getWeatherList(): Response<WeatherResponse>
}