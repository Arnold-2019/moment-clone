package com.example.wechatclone

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wechatclone.data.Tweet
import kotlinx.android.synthetic.main.fragment_tweet.view.*

class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(tweet: Tweet) {
//        Glide.with(itemView.context)
//            .load("https://thoughtworks-mobile-2018.herokuapp.com/images/user/avatar.png")
//            .placeholder(R.drawable.ic_launcher_foreground)
//            .into(itemView.avatar)
        itemView.user_name.text = tweet.content
        itemView.content_text_view.text = tweet.content
    }
}