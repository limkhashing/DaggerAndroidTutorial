package com.limkhashing.daggerandroidtutorial.ui.main

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
    val status: AuthStatus,
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T? = null)
        : Resource<T>(AuthStatus.Success, data)

    class Loading<T>(data: T? = null)
        : Resource<T>(AuthStatus.LOADING, data)

    class Error<T>(message: String, data: T? = null)
        : Resource<T>(AuthStatus.ERROR, data, message)

    enum class AuthStatus {
        Success,
        ERROR,
        LOADING
    }
}