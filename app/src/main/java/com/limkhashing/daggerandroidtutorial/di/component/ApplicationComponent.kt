package com.limkhashing.daggerandroidtutorial.di.component

import android.app.Application
import android.se.omapi.Session
import com.limkhashing.daggerandroidtutorial.BaseApplication
import com.limkhashing.daggerandroidtutorial.di.module.ActivityModule
import com.limkhashing.daggerandroidtutorial.di.module.ApplicationModule
import com.limkhashing.daggerandroidtutorial.di.module.ViewModelModule
import com.limkhashing.daggerandroidtutorial.di.module.auth.AuthViewModelModule
import com.limkhashing.daggerandroidtutorial.session.SessionManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

// this is server
// annotate Singleton means we telling ApplicationComponent
// owns Singleton scope
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        ApplicationModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent : AndroidInjector<BaseApplication> {

    // so the sessionManager is accessible from the application component
    // and will be kept alive as long as the app is running
    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder {
        // don't always have to do
        @BindsInstance
        fun application(application: Application): Builder

        // this is must step for building entry point
        fun build(): ApplicationComponent
    }
}