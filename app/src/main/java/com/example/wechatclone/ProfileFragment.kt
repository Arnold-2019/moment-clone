package com.example.wechatclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.wechatclone.data.UserProfile

class ProfileFragment(private val userProfile: UserProfile) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // bad way to fetch view with activity
        // build in methods to fetch view
        val profileImageView = activity?.findViewById<ImageView>(R.id.profile_image)
        val profileAvatarImageView = activity?.findViewById<ImageView>(R.id.profile_avatar)
        val profileNickTextView = activity?.findViewById<TextView>(R.id.profile_nick)

        if (profileImageView != null) {
            Glide.with(profileImageView.context)
                    .load(userProfile.profileImage)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(profileImageView)

        }
        // scope function 'let, run, with'
        profileImageView?.let {
            Glide.with(profileImageView.context)
                    .load(userProfile.profileImage)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(profileImageView)
        }
        if (profileAvatarImageView != null) {
            Glide.with(profileAvatarImageView.context)
                    .load(userProfile.avatar)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(profileAvatarImageView)
        }
        if (profileNickTextView != null) {
            profileNickTextView.text = userProfile.nick
        }
    }
}
