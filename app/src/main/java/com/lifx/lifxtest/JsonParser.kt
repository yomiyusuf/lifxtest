package com.lifx.lifxtest

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class JsonParser {

    fun makeHttpRequest(url: String): JSONArray? {

        val input:InputStream =  URL(url).openConnection().getInputStream()
        val result = input.bufferedReader().use { it.readText() }
        // try parse the string to a JSON object
        try {
            return JSONArray(result)
        } catch (e: JSONException) {
            Log.e("JSON Parser", "Error parsing data $e")
        }

        return null
    }
}