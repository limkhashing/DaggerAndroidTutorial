package com.limkhashing.daggerandroidtutorial.di.module.main

import com.limkhashing.daggerandroidtutorial.api.main.PostApi
import com.limkhashing.daggerandroidtutorial.di.scope.MainScope
import com.limkhashing.daggerandroidtutorial.ui.main.post.PostAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object MainModule {
    @MainScope
    @Provides
    @JvmStatic
    fun providePostAPI(retrofit: Retrofit) : PostApi {
        return retrofit.create(PostApi::class.java)
    }

    @MainScope
    @Provides
    @JvmStatic
    fun providePostAdapter() : PostAdapter {
        return PostAdapter()
    }
}