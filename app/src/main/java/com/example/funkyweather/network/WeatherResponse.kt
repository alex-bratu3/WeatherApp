package com.example.funkyweather.network

import java.util.*

data class WeatherResponse(val features: List<Feature>)

data class Feature(val properties: Properties)

data class Properties(
    val event: String,
    val effective: Date,
    val ends: Date,
    val senderName: String
)