package com.lifx.lifxtest.model

import com.google.gson.annotations.SerializedName

data class ListItem(
    val title: String,

    @SerializedName("image_url")
    val imageUrl: String)