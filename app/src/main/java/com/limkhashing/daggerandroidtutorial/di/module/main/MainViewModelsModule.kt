package com.limkhashing.daggerandroidtutorial.di.module.main

import androidx.lifecycle.ViewModel
import com.limkhashing.daggerandroidtutorial.di.scope.AuthViewModelKey
import com.limkhashing.daggerandroidtutorial.ui.main.post.PostViewModel
import com.limkhashing.daggerandroidtutorial.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {
    @Binds
    @IntoMap
    @AuthViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @AuthViewModelKey(PostViewModel::class)
    abstract fun bindPostViewModel(viewModel: PostViewModel): ViewModel
}