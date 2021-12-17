package com.buffedapps.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.buffedapps.weatherapp.Activities.WeatherViewModel
import com.buffedapps.weatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mWeatherDetails: Weather
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel= ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )[WeatherViewModel::class.java]

        mWeatherDetails=viewModel.weatherDetails!!

        updateDetails()
        configureActionBar()
    }
    private fun configureActionBar(){
        setSupportActionBar(binding.toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
    }
    private fun updateDetails() {
        binding.temperature.text=mWeatherDetails.temp
        binding.windSpeed.text=mWeatherDetails.wind_speed
        GlideLoader(this).loadPicture(mWeatherDetails.weatherIcon,binding.weatherIcon)
        binding.pressure.text=mWeatherDetails.pressure
        binding.precipitation.text=mWeatherDetails.precipitation
        binding.cloudCover.text=mWeatherDetails.cloudCover

        binding.location.text=mWeatherDetails.region+", "+mWeatherDetails.country
        binding.time.text="Updated at: "+mWeatherDetails.time
        binding.weatherDesc.text=mWeatherDetails.weatherDesc
        binding.feelsLike.text="Feels "+mWeatherDetails.feelsLike+"°C"
    }
}