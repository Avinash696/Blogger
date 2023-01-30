package com.example.blogger

import android.app.Application
import com.example.blogger.data.BlogService
import com.example.blogger.data.Repositary
import com.example.blogger.data.RetrofitHelper

class BlogApplication : Application() {
    lateinit var repositary: Repositary
    override fun onCreate() {
        initilizer()
        super.onCreate()
    }

    private fun initilizer() {
        val blogService = RetrofitHelper.getRetrofitClient()!!.create(BlogService::class.java)
        repositary = Repositary(blogService)
    }

}