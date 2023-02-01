package com.example.blogger.blogModels

import java.io.Serializable

data class blogModel(
    val etag: String,
    val items: List<Item>,
    val kind: String
): Serializable