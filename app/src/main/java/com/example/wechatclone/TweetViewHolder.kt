package com.example.wechatclone

import android.view.View
import android.widget.GridView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wechatclone.data.Tweet
import kotlinx.android.synthetic.main.fragment_tweet.view.avatar
import kotlinx.android.synthetic.main.fragment_tweet.view.tweet_content
import kotlinx.android.synthetic.main.fragment_tweet.view.user_name

class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(tweet: Tweet) {
        // avatar, user name, tweet content
        Glide.with(itemView.context)
            .load(tweet.sender.avatar)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(itemView.avatar)
        itemView.user_name.text = tweet.sender.nick
        itemView.tweet_content.text = tweet.content

        // images
        val imageGridView = itemView.findViewById<View>(R.id.grid_view) as GridView
        if (!tweet.images.isNullOrEmpty()) {
            imageGridView?.let {
                it.adapter = GridViewAdapter(tweet.images, itemView).getGridViewAdapter()
            }
        }

        // comments
        val commentRecyclerView = itemView.findViewById<RecyclerView>(R.id.comment_recycler_view)
        if (!tweet.comments.isNullOrEmpty()) {
            commentRecyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = CommentAdapter(tweet.comments)
            }
        }
    }
}
