package com.limkhashing.daggerandroidtutorial.di.module.main

import com.limkhashing.daggerandroidtutorial.ui.main.post.PostFragment
import com.limkhashing.daggerandroidtutorial.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostFragment(): PostFragment
}
