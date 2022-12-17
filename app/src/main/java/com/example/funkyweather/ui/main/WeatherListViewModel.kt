package com.example.funkyweather.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.funkyweather.network.Feature
import com.example.funkyweather.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherListViewModel(private val repository: WeatherRepository) : ViewModel() {

    sealed class UiModel {
        object ShowLoading : UiModel()
        object HideLoading : UiModel()
        data class Error(val errorMessage: String) : UiModel()
        data class Results(val responseList: List<Feature>) : UiModel()
    }

    private val model = MutableLiveData<UiModel>()
    fun model(): LiveData<UiModel> = model

    fun onViewCreated() {
        model.value = UiModel.ShowLoading

        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getWeatherItems()
            withContext(Dispatchers.Main) {
                model.value = UiModel.HideLoading
                if (result.isSuccessful) {
                    result.body()?.let {
                        model.value = UiModel.Results(it.features)
                    }
                } else {
                    result.errorBody()?.let { result.errorBody().toString() }
                }
            }
        }
    }
}