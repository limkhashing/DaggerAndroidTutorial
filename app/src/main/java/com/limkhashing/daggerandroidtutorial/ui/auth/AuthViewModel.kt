package com.limkhashing.daggerandroidtutorial.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.limkhashing.daggerandroidtutorial.api.auth.AuthApi
import com.limkhashing.daggerandroidtutorial.api.models.User
import com.limkhashing.daggerandroidtutorial.session.SessionManager
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private val TAG = AuthViewModel::class.java.simpleName

class AuthViewModel
@Inject constructor(private val authApi: AuthApi, private val sessionManager: SessionManager) : ViewModel() {

    fun authenticateUserWithID(userID: Int) {
        sessionManager.authenticateUserWithID(queryUserID(userID))
    }

    private fun queryUserID(userID: Int): LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userID)
                // instead calling onError, we call this error function to return invalid User
                .onErrorReturn {
                    Log.d(TAG, it.message.toString())
                    return@onErrorReturn User(id = -1)
                }
                // if there is error, id is -1, no error then it would be normal user
                .map {
                    if (it.id == -1)
                        return@map AuthResource.Error("Could not authenticate", it)
                    return@map AuthResource.Authenticated(it)
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }
}
