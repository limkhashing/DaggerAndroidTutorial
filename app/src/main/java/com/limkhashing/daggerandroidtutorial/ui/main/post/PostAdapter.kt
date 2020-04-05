package com.limkhashing.daggerandroidtutorial.ui.main.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.limkhashing.daggerandroidtutorial.R
import com.limkhashing.daggerandroidtutorial.api.models.PostItem
import java.util.*


class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private var posts: List<PostItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.view_item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = posts[position].title
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<PostItem>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
    }
}
