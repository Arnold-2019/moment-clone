package com.example.wechatclone

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wechatclone.data.Tweet
import kotlinx.android.synthetic.main.fragment_tweet.view.*

class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(tweet: Tweet) {
        if (tweet.error.isNullOrEmpty() && tweet.unknownError.isNullOrEmpty()) {
            Glide.with(itemView.context)
                .load(tweet.sender.avatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(itemView.avatar)
            itemView.user_name.text = tweet.sender.userName
            itemView.content_text_view.text = tweet.content
        }
    }
}