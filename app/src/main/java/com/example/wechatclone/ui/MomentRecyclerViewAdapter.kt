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
import kotlinx.android.synthetic.main.fragment_profile.view.*
import kotlinx.android.synthetic.main.fragment_tweet.view.*

class MomentRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var tweets: List<Tweet> = listOf()
    private var userProfile: UserProfile = UserProfile(
        profileImage = "",
        avatar = "",
        nick = "nick name",
        userName = "user name"
    )

    companion object {
        private const val VIEW_TYPE_PROFILE = 1
        private const val VIEW_TYPE_TWEET = 2
    }

    sealed class MomentItem {
        object Profile : MomentItem()
        object Tweet : MomentItem()
    }

    private var momentItems: List<MomentItem> = listOf()

    fun refreshPage(userProfile: UserProfile, tweets: List<Tweet>) {
        this.tweets = tweets
        this.userProfile = userProfile
        refreshMomentItems()
        notifyDataSetChanged()
    }

    private fun refreshMomentItems() {
        val items = mutableListOf<MomentItem>()
        items.add(MomentItem.Profile)
        for (index in tweets.indices) {
            items.add(MomentItem.Tweet)
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
        private val imageGridViewUtil = ImageGridViewUtil(itemView)

        fun bind(tweet: Tweet) {
            // avatar, username, content
            with(itemView) {
                Glide.with(this)
                    .load(tweet.sender?.avatar)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(this.avatar)
                user_name.text = tweet.sender?.nick ?: ""
                tweet_content.text = tweet.content
            }

            // images
            val imageGridView = itemView.findViewById<View>(R.id.grid_view) as GridView
            if (!tweet.images.isNullOrEmpty()) {
                with(imageGridView) {
                    layoutParams.height = imageGridViewUtil.getDynamicHeight(tweet)
                    layoutParams.width = imageGridViewUtil.getDynamicWidth(tweet)
                    numColumns = imageGridViewUtil.getDynamicNumColumns(tweet)
                    adapter = ImageGridViewAdapter(tweet.images, itemView).getAdapter()
                }
            }

            // comments
            val commentRecyclerView =
                itemView.findViewById<RecyclerView>(R.id.comment_recycler_view)
            if (!tweet.comments.isNullOrEmpty()) {
                commentRecyclerView.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = CommentRecyclerViewAdapter(tweet.comments)
                }
            }
        }
    }
}
