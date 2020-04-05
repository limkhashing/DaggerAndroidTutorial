package com.limkhashing.daggerandroidtutorial.di.module.auth

import com.limkhashing.daggerandroidtutorial.api.auth.AuthApi
import com.limkhashing.daggerandroidtutorial.di.scope.AuthScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object AuthModule {
    @AuthScope
    @Provides
    @JvmStatic
    fun authAPI(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}
