package com.limkhashing.daggerandroidtutorial.di.module.auth

import androidx.lifecycle.ViewModel
import com.limkhashing.daggerandroidtutorial.di.scope.AuthViewModelKey
import com.limkhashing.daggerandroidtutorial.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

// this class is for dependencies for AuthViewModel itself
@Module
abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @AuthViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(viewModel: AuthViewModel): ViewModel
}