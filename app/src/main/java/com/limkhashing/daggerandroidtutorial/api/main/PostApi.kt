package com.limkhashing.daggerandroidtutorial.api.main

import com.limkhashing.daggerandroidtutorial.api.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface PostApi {
    @GET("posts")
    fun getPostsFromUser(@Query("userId") id: Int): Flowable<Post>
}