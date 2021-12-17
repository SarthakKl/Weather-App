package com.buffedapps.weatherapp

data class Weather(
    val temp:String,
    val wind_speed:String,
    val region:String,
    val country:String,
    val pressure:String,
    val precipitation:String,
    val cloudCover:String,
    val weatherDesc:String,
    val feelsLike:String,
    val weatherIcon:String,
    val time:String
)