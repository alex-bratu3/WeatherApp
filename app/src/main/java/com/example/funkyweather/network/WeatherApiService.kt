package com.example.funkyweather.network

import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiService {
    @GET("/alerts/active?status=actual&message_type=alert")
    suspend fun getList(): Response<WeatherResponse>
}