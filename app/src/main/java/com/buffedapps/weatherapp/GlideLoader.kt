package com.buffedapps.weatherapp

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class Glide(val context: Context) {
    fun loadPicture(imageUrl:Any,image: ImageView){
        Glide
            .with(context)
            .load(imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_user_placeholder)
            .into(image)
    }
}