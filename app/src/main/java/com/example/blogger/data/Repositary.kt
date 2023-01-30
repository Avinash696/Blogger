package com.example.blogger.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.blogger.data.blogModels.blogModel

class Repositary(private val blogService: BlogService) {
    private val TAG = "Repositary"
    private var blogMutableLiveData = MutableLiveData<blogModel>()
    val blogLiveData = blogMutableLiveData

    suspend fun getAllBlogRepositary(key: String) {
//        val result = blogService.getBlog(key)
        val result = blogService.getBlog()
        if (result.isSuccessful) {
            blogMutableLiveData.postValue(result.body())
            Log.d(TAG, "Success: " + result.body())
        } else {
            Log.d(TAG, "Error: " + result)
        }
    }
}