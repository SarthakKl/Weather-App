package com.buffedapps.weatherapp.Activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.buffedapps.weatherapp.FetchWeatherData

class WeatherViewModel(application: Application):AndroidViewModel(application) {
    val weatherDetails=FetchWeatherData.weatherDetails
}