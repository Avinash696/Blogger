package com.example.blogger.data.blogModels

import java.io.Serializable

data class Item(
    val author: Author,
    val blog: Blog,
    val content: String,
    val etag: String,
    val id: String,
    val kind: String,
    val published: String,
    val replies: Replies,
    val selfLink: String,
    val title: String,
    val updated: String,
    val url: String
): Serializable