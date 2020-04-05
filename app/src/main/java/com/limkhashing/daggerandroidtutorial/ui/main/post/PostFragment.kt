package com.limkhashing.daggerandroidtutorial.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.limkhashing.daggerandroidtutorial.R
import com.limkhashing.daggerandroidtutorial.ui.main.Resource
import com.limkhashing.daggerandroidtutorial.utils.VerticalSpaceItemDecoration
import com.limkhashing.daggerandroidtutorial.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_post.*
import javax.inject.Inject


private val TAG = PostFragment::class.java.simpleName

class PostFragment : DaggerFragment() {
    companion object {
        @JvmStatic
        fun newInstance() = PostFragment()
    }

    @Inject lateinit var postAdapter: PostAdapter
    @Inject lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var postViewModel: PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rv_post)

        postViewModel = ViewModelProvider(this, providerFactory).get(PostViewModel::class.java)
        initRecyclerView()
        subscribeObserver()
    }

    private fun subscribeObserver() {
        postViewModel.observePost().observe(viewLifecycleOwner, Observer {
            it?.let {
//                it.data?.let { data-> postAdapter.setPosts(data) }
                when (it.status) {
                    Resource.AuthStatus.Success -> it.data?.let { data-> postAdapter.setPosts(data) }
                    Resource.AuthStatus.ERROR -> Log.d(TAG, "onChanged: PostsFragment: ERROR... " + it.message);
                    Resource.AuthStatus.LOADING -> Log.d(TAG, "onChanged: PostsFragment: LOADING...");
                }
            }
        })
    }

    private fun initRecyclerView() {
        rv_post.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpaceItemDecoration(15)
        rv_post.addItemDecoration(itemDecoration)
        rv_post.adapter = postAdapter
    }
}
