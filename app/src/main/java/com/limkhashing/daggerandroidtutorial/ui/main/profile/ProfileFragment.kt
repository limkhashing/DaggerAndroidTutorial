package com.limkhashing.daggerandroidtutorial.ui.main.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.limkhashing.daggerandroidtutorial.R
import com.limkhashing.daggerandroidtutorial.api.models.User
import com.limkhashing.daggerandroidtutorial.ui.auth.AuthResource
import com.limkhashing.daggerandroidtutorial.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private val TAG = ProfileFragment::class.java.simpleName

class ProfileFragment : DaggerFragment() {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment ProfileFragment.
         */
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "Profile Fragment")
        profileViewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)
        subscribeObserver()
    }

    private fun subscribeObserver() {
        // use viewLifecycleOwner because Fragment can get destroy by Android system
        // since fragment have its own lifecycle
        profileViewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> setUserDetails(it.data)
                    AuthResource.AuthStatus.ERROR -> setErrorDetails(it.message)
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {}
                    else -> { }
                }
            }
        })
    }

    private fun setErrorDetails(message: String?) {
        message?.let {
            email.text = it
            username.text = "error"
            website.text = "error"
        }
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }
}
