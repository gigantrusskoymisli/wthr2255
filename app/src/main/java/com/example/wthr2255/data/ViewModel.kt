package com.example.wthr2255.data

import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ViewModel
@Inject
constructor() : ViewModel() {
    var result: WthrObj? = null
    var isLoading = mutableStateOf(false)

    fun getWeather() = viewModelScope.launch {
        val resp = AppModule.providesApiService(AppModule.providesMoshi()).GetWeather()

        if (resp.isSuccessful && resp.body() != null) {
            isLoading.value = true
            result = resp.body()!!
        }
    }
}