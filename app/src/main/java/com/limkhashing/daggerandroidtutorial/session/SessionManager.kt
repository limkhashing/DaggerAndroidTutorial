package com.limkhashing.daggerandroidtutorial.session

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.limkhashing.daggerandroidtutorial.api.models.User
import com.limkhashing.daggerandroidtutorial.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

private val TAG = SessionManager::class.java.simpleName

// All the set user will be in here
// This class is holding observable object
@Singleton
class SessionManager @Inject constructor() {

    // use MediatorLiveData so that we can observe the authenticated user
    // in any class that injected SessionManager into. So, if user logout
    // we can react to that change.
    private val cachedUser = MediatorLiveData<AuthResource<User>>()

    fun authenticateUserWithID(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.Loading(null)
        cachedUser.addSource(source) {
            cachedUser.value = it
            cachedUser.removeSource(source)
        }
    }

    fun logout() {
        Log.d(TAG, "Logged out")
        cachedUser.value = AuthResource.Logout("You have logged out")
    }

    fun getAuthUser(): LiveData<AuthResource<User>> {
        return cachedUser
    }
}
