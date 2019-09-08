package com.lifx.lifxtest.network

import com.lifx.lifxtest.ListItem
import retrofit2.Call
import retrofit2.http.GET

interface ListService {
    @GET("themes/v1/curated")
    fun getList() : Call<List<ListItem>>
}