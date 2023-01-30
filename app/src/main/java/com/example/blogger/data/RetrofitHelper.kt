package com.example.blogger.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val url = "https://www.googleapis.com/"
//    https://www.googleapis.com/blogger/v3/blogs/4694208272497542694/posts?key=AIzaSyCwhBcyvZm8jQFIWwJNOfNUV3qRyW00Ces

    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}