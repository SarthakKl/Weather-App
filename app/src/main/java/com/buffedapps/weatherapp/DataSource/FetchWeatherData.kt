package com.buffedapps.weatherapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class FetchWeatherData(private val context: Context, private val city:String) {
    companion object{
        var weatherDetails: Weather?=null
    }
    fun getJsonData(){

        val queue= Volley.newRequestQueue(context)
        val url="http://api.weatherstack.com/current?access_key=${Constants.API_KEY}&query=${city}"

        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                saveData(response)
            },
            { error ->
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        jsonRequest.retryPolicy = DefaultRetryPolicy(
            10000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        queue.add(jsonRequest)
    }

    private fun saveData(response: JSONObject) {
        weatherDetails= Weather(
            response.getJSONObject("current").getString("temperature"),
            response.getJSONObject("current").getString("wind_speed"),
            city + ", " + response.getJSONObject("location").getString("region"),
            response.getJSONObject("location").getString("country"),
            response.getJSONObject("current").getString("pressure"),
            response.getJSONObject("current").getString("precip"),
            response.getJSONObject("current").getString("cloudcover"),
            response.getJSONObject("current").getJSONArray("weather_descriptions")[0].toString(),
            response.getJSONObject("current").getString("feelslike"),
            response.getJSONObject("current").getJSONArray("weather_icons")[0].toString(),
            response.getJSONObject("location").getString("localtime")
        )
        Log.e("Weather", weatherDetails!!.temp.toString())
        val intent = Intent(context, MainActivity::class.java)
        intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

}