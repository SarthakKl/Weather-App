package com.buffedapps.weatherapp

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideLoader(val context: Context) {
    fun loadPicture(imageUrl:Any,image: ImageView){
        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.weather_placeholder)
            .into(image)
    }
}