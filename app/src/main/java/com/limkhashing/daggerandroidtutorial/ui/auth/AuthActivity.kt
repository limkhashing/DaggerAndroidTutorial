package com.limkhashing.daggerandroidtutorial.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.limkhashing.daggerandroidtutorial.R
import com.limkhashing.daggerandroidtutorial.ui.auth.AuthResource.AuthStatus.*
import com.limkhashing.daggerandroidtutorial.ui.main.MainActivity
import com.limkhashing.daggerandroidtutorial.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

private val TAG = AuthActivity::class.java.simpleName

class AuthActivity : DaggerAppCompatActivity() {

    @Inject lateinit var logo: Drawable
    @Inject lateinit var requestManager: RequestManager
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setLogo()

        authViewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        login_button.setOnClickListener { attemptLogin() }
        subscribeObserver()
    }

    private fun subscribeObserver() {
        authViewModel.observeAuthState().observe(this, Observer {
            if (it != null)
                when (it.status) {
                    AUTHENTICATED -> {
                        showProgressBar(false)
                        onLoginSuccess()
                    }
                    ERROR ->  {
                        showProgressBar(false)
                        Log.d(TAG, "Error")
                    }
                    LOADING -> showProgressBar(true)
                    NOT_AUTHENTICATED -> showProgressBar(false)
                }
        })
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun showProgressBar(show: Boolean) {
        if (show)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.GONE
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(user_id_input.text.toString()))
            return
        authViewModel.authenticateUserWithID(user_id_input.text.toString().toInt())
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }
}
