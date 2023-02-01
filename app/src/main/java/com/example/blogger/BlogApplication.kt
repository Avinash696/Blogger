package com.example.blogger

import android.app.Application
import androidx.room.Room
import com.example.blogger.data.BlogService
import com.example.blogger.data.Repositary
import com.example.blogger.data.RetrofitHelper
import com.example.blogger.database.ItemDatabase

class BlogApplication : Application() {
    lateinit var repositary: Repositary
    override fun onCreate() {
        initilizer()
        super.onCreate()
    }

    private fun initilizer() {
        val blogService = RetrofitHelper.getRetrofitClient().create(BlogService::class.java)
        val databaseService = ItemDatabase.getDatabase(applicationContext)
        repositary = Repositary(applicationContext,blogService, databaseService!!)
    }
}