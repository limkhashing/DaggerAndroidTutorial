package com.limkhashing.daggerandroidtutorial.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.limkhashing.daggerandroidtutorial.api.models.User
import com.limkhashing.daggerandroidtutorial.session.SessionManager
import com.limkhashing.daggerandroidtutorial.ui.auth.AuthResource
import javax.inject.Inject

private val TAG = ProfileViewModel::class.java.simpleName

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager): ViewModel() {

    fun getAuthenticatedUser(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }
}
