package com.limkhashing.daggerandroidtutorial.di.module

import com.limkhashing.daggerandroidtutorial.di.module.auth.AuthModule
import com.limkhashing.daggerandroidtutorial.di.module.auth.AuthViewModelModule
import com.limkhashing.daggerandroidtutorial.di.module.main.MainFragmentModule
import com.limkhashing.daggerandroidtutorial.di.module.main.MainModule
import com.limkhashing.daggerandroidtutorial.di.module.main.MainViewModelsModule
import com.limkhashing.daggerandroidtutorial.di.scope.AuthScope
import com.limkhashing.daggerandroidtutorial.di.scope.MainScope
import com.limkhashing.daggerandroidtutorial.ui.auth.AuthActivity
import com.limkhashing.daggerandroidtutorial.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Sub-component is define here because ContributesAndroidInjector
// Define any of the possible activity in this class
// that is potential client that can inject dependencies into
@Module
abstract class ActivityModule {

    // Only the AuthActivity sub-component able to use the modules
    // ContributesAndroidInjector generates sub-component behind the scene
    // Sub-component utilized for organization and scoping
    // Specifying modules for the sub-component to use
    // can only be accessible for the Sub-component
    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(modules = [
        MainModule::class,
        MainFragmentModule::class,
        MainViewModelsModule::class
    ])
    abstract fun contributeMainActivity(): MainActivity

    // new way to put static method in kotlin since 2.26
//    companion object {
//        @Provides fun testString(): String {
//            return "this is test"
//        }
//    }
}
