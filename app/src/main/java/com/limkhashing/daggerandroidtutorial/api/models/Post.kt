package com.limkhashing.daggerandroidtutorial.api.models


class Post : ArrayList<PostItem>()

data class PostItem(
    val body: String? = null,
    val id: Int,
    val title: String? = null,
    val userId: Int? = null
)
