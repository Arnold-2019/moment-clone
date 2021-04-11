package com.example.wechatclone

import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.SimpleAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wechatclone.data.Tweet
import kotlinx.android.synthetic.main.fragment_tweet.view.*
import java.util.ArrayList
import java.util.HashMap

class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mCircleGridView: GridView? = null

    fun bind(tweet: Tweet) {
        if (tweet.error.isNullOrEmpty() && tweet.unknownError.isNullOrEmpty()) {
            // avatar, user name, tweet content
            Glide.with(itemView.context)
                .load(tweet.sender.avatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(itemView.avatar)
            itemView.user_name.text = tweet.sender.nick
            itemView.content_text_view.text = tweet.content

            // tweet images
            var imageUrls = emptyList<String>()
            if (tweet.images != null && tweet.images.isNotEmpty()) {
                imageUrls = tweet.images.map { it.url }
            }

            if (imageUrls.isNotEmpty()) {
                mCircleGridView = itemView.findViewById<View>(R.id.grid_view) as GridView
                mCircleGridView?.let {
                    it.adapter = getGridViewAdapter(imageUrls, itemView)
                }
            }

            // tweet comments
            val comments = getComments(tweet)
            val commentRecyclerView = itemView.findViewById<RecyclerView>(R.id.comment_recycler_view)
            commentRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CommentAdapter(comments)
            }
        }
    }

    private fun getGridViewAdapter(imageUrls: List<String>, itemView: View): SimpleAdapter {
        val list = ArrayList<HashMap<String, String?>>()
        imageUrls.forEach {
            val map = HashMap<String, String?>()
            map["ItemImage"] = it
            list.add(map)
        }

        val adapter = SimpleAdapter(itemView.context, list, R.layout.image_item, arrayOf("ItemImage"), intArrayOf(R.id.imageView))
        adapter.viewBinder = SimpleAdapter.ViewBinder { view, data, textRepresentation ->
            if (view is ImageView) {
                GlideUtil.glideWithPlaceHolder(itemView.context, data.toString()).into(view)
                true
            } else false
        }
        return adapter
    }

    private fun getComments(tweet: Tweet): MutableList<String> {
        val comments = mutableListOf<String>()
        if (!tweet.comments.isNullOrEmpty()) {
            tweet.comments.forEach {
                comments.add("${it.sender.nick}: ${it.content}")
            }
        }
        return comments
    }
}