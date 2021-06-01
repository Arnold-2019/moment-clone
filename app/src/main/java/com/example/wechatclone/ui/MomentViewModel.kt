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

    private val allTweets: MutableLiveData<List<Tweet>> = MutableLiveData((mutableListOf()))

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
            allTweets.postValue(it)
            loadMoreTweets()
        }
    }

    fun getUserProfile() {
        repository.searchUserProfile {
            _profile.postValue(it)
        }
    }

    fun loadMoreTweets() {
        val pageSize = 5
        val numberOfTweets = allTweets.value?.size ?: 0
        val startPoint = tweets.value?.size ?: 0
        val endPoint =
            if (startPoint + pageSize > numberOfTweets) numberOfTweets else startPoint + pageSize
        val tweetList: MutableList<Tweet> = mutableListOf()
        for (index in 0 until endPoint) {
            tweetList.add(allTweets.value!![index])
        }
        _tweets.value = tweetList
    }
}
