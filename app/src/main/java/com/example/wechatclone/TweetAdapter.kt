package com.example.wechatclone

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechatclone.data.Tweet

class TweetAdapter(private val tweets: List<Tweet>): RecyclerView.Adapter<TweetViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_tweet, parent, false)
        return TweetViewHolder(itemView)
    }

    override fun getItemCount() = tweets.size

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        return holder.bind(tweets[position])
    }
}