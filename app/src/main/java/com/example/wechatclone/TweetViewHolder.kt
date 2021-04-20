package com.example.wechatclone

import android.view.View
import android.widget.GridView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wechatclone.data.Tweet
import com.example.wechatclone.data.UserComment
import kotlinx.android.synthetic.main.fragment_tweet.view.avatar
import kotlinx.android.synthetic.main.fragment_tweet.view.tweet_content
import kotlinx.android.synthetic.main.fragment_tweet.view.user_name

class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    private var mCircleGridView: GridView? = null

    fun bind(tweet: Tweet) {

        // tweet should already be valid
        if (tweet.error.isNullOrEmpty() && tweet.unknownError.isNullOrEmpty()) {
            // avatar, user name, tweet content
            Glide.with(itemView.context)
                .load(tweet.sender.avatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(itemView.avatar)

            itemView.user_name.text = tweet.sender.nick
            itemView.tweet_content.text = tweet.content

            // tweet images
            var imageUrls = emptyList<String>()
            if (tweet.images != null && tweet.images.isNotEmpty()) {
                imageUrls = tweet.images.map { it.url }
            }

            if (imageUrls.isNotEmpty()) {
                mCircleGridView = itemView.findViewById<View>(R.id.grid_view) as GridView
                mCircleGridView?.let {
                    it.adapter = GridViewAdapter(imageUrls, itemView).getGridViewAdapter()
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

    private fun getComments(tweet: Tweet): MutableList<UserComment> {

        val comments = mutableListOf<UserComment>()
        if (!tweet.comments.isNullOrEmpty()) {
            tweet.comments.forEach { comments.add(it) }
        }
        return comments
    }
}
