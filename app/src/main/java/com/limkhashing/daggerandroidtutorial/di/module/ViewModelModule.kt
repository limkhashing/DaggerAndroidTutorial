package com.limkhashing.daggerandroidtutorial.di.module

import androidx.lifecycle.ViewModelProvider
import com.limkhashing.daggerandroidtutorial.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

// this class is responsible for generating dependencies for view model factory
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory)
            : ViewModelProvider.Factory
}