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
    private val pageSize = 5

    private var allTweets: List<Tweet> = mutableListOf()

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
            allTweets = it
        }
    }

    fun getUserProfile() {
        repository.searchUserProfile {
            _profile.postValue(it)
        }
    }

    fun loadMoreTweets() {
        val numberOfTweets = allTweets.size
        val startPoint = _tweets.value?.size ?: 0
        val endPoint =
            if (startPoint + pageSize >= numberOfTweets) numberOfTweets else startPoint + pageSize
        val tweetList: MutableList<Tweet> = _tweets.value as MutableList<Tweet>
        for (index in startPoint until endPoint) {
            tweetList.add(allTweets[index])
        }
        _tweets.value = tweetList
    }

    fun refreshTweetList() {
        _tweets.value = mutableListOf()
        loadMoreTweets()
    }
}
