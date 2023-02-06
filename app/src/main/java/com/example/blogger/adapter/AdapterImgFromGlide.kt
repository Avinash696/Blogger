package com.example.blogger.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imgFromGlide")
fun ImageView.imgFromUrl(url:String){
    Glide.with(this.context).load(url).into(this)
}