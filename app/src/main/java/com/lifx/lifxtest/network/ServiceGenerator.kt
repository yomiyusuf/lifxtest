package com.lifx.lifxtest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {
    companion object {
        private const val BASE_URL = "https://cloud.lifx.com/"

        private val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        private var retrofit = builder.build()
        private val httpClient = OkHttpClient.Builder()
        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        fun <S> createService(serviceClass: Class<S>): S {
            httpClient.interceptors().clear()
            httpClient.addInterceptor{ chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .build()
                chain.proceed(request)
            }
            httpClient.addInterceptor(logging)
            builder.client(httpClient.build())
            retrofit = builder.build()

            return retrofit.create(serviceClass)
        }

    }
}