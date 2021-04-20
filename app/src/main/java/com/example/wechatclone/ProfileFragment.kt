package com.example.wechatclone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.wechatclone.data.UserProfile
import kotlinx.android.synthetic.main.fragment_profile.profile_avatar
import kotlinx.android.synthetic.main.fragment_profile.profile_image
import kotlinx.android.synthetic.main.fragment_profile.profile_nick

class ProfileFragment(private val userProfile: UserProfile) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // scope function 'let, run, with'
        profile_image?.let {
            Glide.with(it.context)
                    .load(userProfile.profileImage)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(it)
        }

        profile_avatar?.let {
            Glide.with(it.context)
                    .load(userProfile.avatar)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(it)
        }

        profile_nick?.let {
            it.text = userProfile.nick
        }
    }
}
