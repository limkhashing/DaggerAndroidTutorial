package com.limkhashing.daggerandroidtutorial.ui.main.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.limkhashing.daggerandroidtutorial.api.main.PostApi
import com.limkhashing.daggerandroidtutorial.api.models.Post
import com.limkhashing.daggerandroidtutorial.api.models.PostItem
import com.limkhashing.daggerandroidtutorial.session.SessionManager
import com.limkhashing.daggerandroidtutorial.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private val TAG = PostViewModel::class.java.simpleName

class PostViewModel @Inject constructor(private val sessionManager: SessionManager,
                                        private val postApi: PostApi): ViewModel() {

    private val posts = MediatorLiveData<Resource<Post>>()

    fun observePost(): LiveData<Resource<Post>> {
        posts.value = Resource.Loading(null)
        val source =  LiveDataReactiveStreams.fromPublisher(
            postApi.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id!!)
                .onErrorReturn {
                    Log.d(TAG, it.message.toString())
                    return@onErrorReturn Post().apply { add(PostItem(id = -1)) }
                }
                .map {
                    if (it[0].id == -1)
                        return@map Resource.Error("Something wrong", null)
                    return@map Resource.Success(it)
                }
                .subscribeOn(Schedulers.io())
        )
        posts.addSource(source) {
            posts.value = it as Resource<Post>
            posts.removeSource(source)
        }
        return posts
    }
}
