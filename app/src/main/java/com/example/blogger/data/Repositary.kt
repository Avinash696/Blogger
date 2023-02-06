package com.example.blogger.data

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import com.example.blogger.blogModels.blogModel
import com.example.blogger.database.DaoInterface
import com.example.blogger.database.DatabaseModel
import com.example.blogger.database.ItemDatabase
import com.example.blogger.utils.InternetConnection

class Repositary(
    private val context: Context,
    private val blogService: BlogService,
    private val database: ItemDatabase
) {
    private val TAG = "Repositary"
    private var blogMutableLiveData = MutableLiveData<blogModel>()
    val blogLiveData: LiveData<blogModel> = blogMutableLiveData

    suspend fun getAllBlogRepositary(key: String) {
        if (InternetConnection.isInternetAvailable(context)) {
            val result = blogService.getBlog()
            if (result.isSuccessful) {
                blogMutableLiveData.postValue(result.body())
                database.itemDao().addItems(result.body()!!.items)
                Log.d(TAG, "with internet : ${result.body()}")
            }
        } else {
            // no internet
            blogMutableLiveData.postValue(
                blogModel(
                    "customTag", database.itemDao().getItems(), "customKind"
                )
            )
            Log.d(TAG, "no internet : $blogMutableLiveData")
        }
//        val result = blogService.getBlog(key)
    }
}
