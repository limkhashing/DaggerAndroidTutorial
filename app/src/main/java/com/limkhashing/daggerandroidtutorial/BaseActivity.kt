package com.limkhashing.daggerandroidtutorial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.limkhashing.daggerandroidtutorial.session.SessionManager
import com.limkhashing.daggerandroidtutorial.ui.auth.AuthActivity
import com.limkhashing.daggerandroidtutorial.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

private val TAG = BaseActivity::class.java.simpleName

abstract class BaseActivity : DaggerAppCompatActivity() {

    protected abstract val layoutResource: Int

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)
        observeAuthState()
    }

    private fun observeAuthState() {
        sessionManager.getAuthUser().observe(this, Observer {
            if (it != null)
                when (it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> Log.d(TAG, "Authenticated")
                    AuthResource.AuthStatus.ERROR -> Log.d(TAG, "Error")
                    AuthResource.AuthStatus.LOADING -> { }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> navLoginScreen()
                }
        })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}