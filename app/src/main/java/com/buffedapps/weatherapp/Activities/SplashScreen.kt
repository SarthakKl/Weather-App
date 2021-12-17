package com.buffedapps.weatherapp.Activities

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.buffedapps.weatherapp.Constants
import com.buffedapps.weatherapp.FetchWeatherData
import com.buffedapps.weatherapp.MainActivity
import com.buffedapps.weatherapp.R
import com.google.android.gms.location.*
import java.util.*


class SplashScreen : AppCompatActivity() {
    private lateinit var mFusedLocation: FusedLocationProviderClient
    private val mRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        mFusedLocation = LocationServices.getFusedLocationProviderClient(this)

        getLastLocation()

    }

    private fun getLastLocation() {
        if (checkForPermission()) {
            if (locationEnable()) {
                mFusedLocation.lastLocation.addOnCompleteListener { task ->
                    val location: Location? = task.result

                    /*if(location==null)
                        //TODO we have to get the last location using LocationRequest*/

                    val geocoder = Geocoder(this, Locale.getDefault())
                    val addresses: List<Address> =
                        geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
                    val cityName: String = addresses[0].locality

                    FetchWeatherData(this, cityName).getJsonData()

                }
            } else {
                Toast.makeText(this, "Please enable your location", Toast.LENGTH_SHORT).show()
            }
        } else {
            requestPermission()
        }
    }

    private fun locationEnable(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }


    private fun checkForPermission(): Boolean {
        return checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            mRequestCode
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == mRequestCode) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                getLastLocation()
        }
    }
}