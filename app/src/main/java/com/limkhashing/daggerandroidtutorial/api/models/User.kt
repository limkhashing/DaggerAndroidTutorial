package com.limkhashing.daggerandroidtutorial.api.models


data class User(
    val email: String? = null,
    var id: Int,
    val name: String? = null,
    val phone: String? = null,
    val username: String? = null,
    val website: String? = null
)
