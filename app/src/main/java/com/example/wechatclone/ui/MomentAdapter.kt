package com.example.wechatclone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wechatclone.R
import com.example.wechatclone.data.Tweet
import com.example.wechatclone.data.UserProfile
import kotlinx.android.synthetic.main.fragment_profile.view.profile_avatar
import kotlinx.android.synthetic.main.fragment_profile.view.profile_image
import kotlinx.android.synthetic.main.fragment_profile.view.profile_nick
import kotlinx.android.synthetic.main.fragment_tweet.view.avatar
import kotlinx.android.synthetic.main.fragment_tweet.view.tweet_content
import kotlinx.android.synthetic.main.fragment_tweet.view.user_name

class MomentAdapter(
        private val userProfile: UserProfile,
        private val tweets: List<Tweet>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_PROFILE = 1
        private const val VIEW_TYPE_TWEET = 2
    }

    sealed class MomentItem {
        object Profile : MomentItem()
        class Tweet(private val tweet: com.example.wechatclone.data.Tweet) : MomentItem()
    }

    private val momentItems: List<MomentItem>

    init {
        val items = mutableListOf<MomentItem>()

        items.add(MomentItem.Profile)

        for (index in tweets.indices) {
            items.add(MomentItem.Tweet(tweets[index]))
        }
        momentItems = items
    }

    override fun getItemViewType(position: Int): Int {
        return when (momentItems[position]) {
            MomentItem.Profile -> VIEW_TYPE_PROFILE
            is MomentItem.Tweet -> VIEW_TYPE_TWEET
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val itemLayout = when (viewType) {
            VIEW_TYPE_PROFILE -> R.layout.fragment_profile
            VIEW_TYPE_TWEET -> R.layout.fragment_tweet
            else -> throw IllegalArgumentException("unknown view type")
        }

        return when (viewType) {
            VIEW_TYPE_PROFILE -> ProfileViewHolder(
                    inflater.inflate(itemLayout, parent, false)
            )
            else -> TweetViewHolder(
                    inflater.inflate(itemLayout, parent, false)
            )
        }
    }

    override fun getItemCount() = momentItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (momentItems[position]) {
            MomentItem.Profile ->
                (holder as ProfileViewHolder).bind(userProfile)

            is MomentItem.Tweet ->
                (holder as TweetViewHolder).bind(tweets[position - 1])
        }
    }

    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(userProfile: UserProfile) {
            itemView.profile_image?.let {
                Glide.with(it.context)
                        .load(userProfile.profileImage)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(it)
            }

            itemView.profile_avatar?.let {
                Glide.with(it.context)
                        .load(userProfile.avatar)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(it)
            }

            itemView.profile_nick?.let {
                it.text = userProfile.nick
            }
        }
    }

    class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tweet: Tweet) {
            // avatar, user name, tweet content
            Glide.with(itemView.context)
                    .load(tweet.sender?.avatar)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(itemView.avatar)
            itemView.user_name.text = tweet.sender?.nick ?: ""
            itemView.tweet_content.text = tweet.content

            // images
            val imageGridView = itemView.findViewById<View>(R.id.grid_view) as GridView
            if (!tweet.images.isNullOrEmpty()) {
                imageGridView?.let {
                    it.adapter = ImageGridViewAdapter(tweet.images, itemView).getAdapter()
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
}
