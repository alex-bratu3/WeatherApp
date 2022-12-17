package com.example.funkyweather.repository

import com.example.funkyweather.network.WeatherResponse
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherItems(): Response<WeatherResponse>
}