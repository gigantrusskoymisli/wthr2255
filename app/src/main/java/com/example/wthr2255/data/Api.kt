package com.example.wthr2255.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    companion object{
        const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    @GET("weather?q=Minsk,BY&appid=f0aa7330fea8334dde16b28f16b375b2")
    suspend fun GetWeather() : Response<WthrObj>
}