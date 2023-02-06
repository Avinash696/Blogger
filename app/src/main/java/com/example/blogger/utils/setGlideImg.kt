package com.example.blogger.utils

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

object setGlideImg {
    fun setImg(context: Context, url: String?, target: ImageView) {
        Glide.with(context).load(url).into(target)
    }

    fun setLogerName(){

    }
}