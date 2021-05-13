package com.aibfive.sample.ui.mvvm

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageUtils {

    @BindingAdapter("url")
    @JvmStatic
    fun loadImage(imageView: ImageView, url : String){
        Glide.with(imageView).load(url).into(imageView)
    }

}