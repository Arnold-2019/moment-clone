package com.example.wechatclone.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wechatclone.data.MomentRepository
import com.example.wechatclone.data.Tweet
import com.example.wechatclone.data.UserProfile

class MomentViewModel : ViewModel() {
    private val _tweets: MutableLiveData<List<Tweet>> = MutableLiveData(mutableListOf())
    val tweets: LiveData<List<Tweet>> = _tweets

    private val _profile: MutableLiveData<UserProfile> = MutableLiveData(
            UserProfile(
                    profileImage = "",
                    avatar = "",
                    nick = "nick name",
                    userName = "user name"
            )
    )
    val profile: LiveData<UserProfile> = _profile

    fun getTweets() {
        MomentRepository.searchTweets {
            _tweets.postValue(it)
        }
    }

    fun getUserProfile() {
        MomentRepository.searchUserProfile {
            _profile.postValue(it)
        }
    }
}
