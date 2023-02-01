package com.example.blogger.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roomItem")
data class DatabaseModel(
    val content: String,
    val etag: String,
    @PrimaryKey
    val id: String,
    val kind: String,
    val published: String,
    val selfLink: String,
    val title: String,
    val updated: String,
    val url: String
)