package com.example.blogger.data

import com.example.blogger.blogModels.blogModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogService {



    @GET("blogger/v3/blogs/4694208272497542694/posts?=")
    suspend  fun getBlog(@Query("key")  key:String): Response<blogModel>
}