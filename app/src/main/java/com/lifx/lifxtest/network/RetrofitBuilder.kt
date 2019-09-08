package com.lifx.lifxtest.network

import com.lifx.lifxtest.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiService: ListApi by lazy {
        retrofitBuilder
            .build()
            .create(ListApi::class.java)
    }
}