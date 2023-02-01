package com.example.blogger.blogModels

import java.io.Serializable

data class Author(
    val displayName: String,
    val id: String,
    val image: Image,
    val url: String
): Serializable