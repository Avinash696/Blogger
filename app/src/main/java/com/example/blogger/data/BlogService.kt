package com.example.blogger.data

import com.example.blogger.data.blogModels.blogModel
import retrofit2.Response
import retrofit2.http.GET

interface BlogService {



    @GET("blogger/v3/blogs/4694208272497542694/posts?key=AIzaSyCwhBcyvZm8jQFIWwJNOfNUV3qRyW00Ces")
    suspend  fun getBlog(): Response<blogModel>
}