package com.example.blogger.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blogger.blogModels.Item

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): DaoInterface

    companion object {
        private var INSTANCE: ItemDatabase? = null

        fun getDatabase(context: Context): ItemDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, ItemDatabase::class.java, "itemDb").fallbackToDestructiveMigration().build()
            }
            return INSTANCE
        }
    }
}