package com.lifx.lifxtest.network

import com.lifx.lifxtest.model.ListItem
import retrofit2.http.GET

interface ListApi {
    @GET("themes/v1/curated")
    suspend fun getList(): List<ListItem>
}