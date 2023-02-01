package com.example.blogger.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.blogger.blogModels.Item

@Dao
interface DaoInterface {

    @Insert
    fun addItems(items:List<Item>)


    @Query("SELECT * FROM roomItem")
    fun getItems():List<Item>
}