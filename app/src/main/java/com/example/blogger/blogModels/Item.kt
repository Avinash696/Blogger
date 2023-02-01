package com.example.blogger.blogModels

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity(tableName = "roomItem")
data class Item(
//    val author: Author,
//    val blog: Blog,
    val content: String,
    val etag: String,
    @PrimaryKey(autoGenerate = true)
    val primaryKey:Int,
    val id: String,
    val kind: String,
    val published: String,
//    val replies: Replies,
    val selfLink: String,
    val title: String,
    val updated: String,
    val url: String
): Serializable