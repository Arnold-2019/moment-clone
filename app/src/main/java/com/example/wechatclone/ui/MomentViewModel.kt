package com.example.wechatclone.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wechatclone.data.MomentRepository
import com.example.wechatclone.data.Tweet
import com.example.wechatclone.data.UserProfile

class MomentViewModel @ViewModelInject constructor(
        private val repository: MomentRepository
) : ViewModel() {
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
        repository.searchTweets {
            _tweets.postValue(it)
        }
    }

    fun getUserProfile() {
        repository.searchUserProfile {
            _profile.postValue(it)
        }
    }
}
