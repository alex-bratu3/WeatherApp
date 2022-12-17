package com.example.funkyweather.di

import com.example.funkyweather.network.NetworkWeatherDataSource
import com.example.funkyweather.network.RemoteWeatherDataSource
import com.example.funkyweather.network.WeatherApiService
import com.example.funkyweather.repository.WeatherRepository
import com.example.funkyweather.repository.WeatherRepositoryImpl
import com.example.funkyweather.ui.main.WeatherListViewModel
import com.example.funkyweather.utils.BASE_URL
import com.example.funkyweather.utils.DATE_FORMAT
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    factory { WeatherListViewModel(get()) }
    factory<RemoteWeatherDataSource> { NetworkWeatherDataSource(get()) }
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }
    factory { provideWeatherApi(get()) }
    single { provideRetrofit() }
}

fun provideRetrofit(): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .setDateFormat(DATE_FORMAT)
        .create()

    return Retrofit.Builder()
        .client(OkHttpClient().newBuilder().build())
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideWeatherApi(retrofit: Retrofit): WeatherApiService =
    retrofit.create(WeatherApiService::class.java)
