package com.example.wechatclone.api

import com.example.wechatclone.data.Tweet
import com.example.wechatclone.data.UserProfile
import retrofit2.Call
import retrofit2.http.GET

interface MomentApi {

    @GET("/user/jsmith/tweets")
    fun getTweets(): Call<List<Tweet>>

    @GET("/user/jsmith")
    fun getProfile(): Call<UserProfile>
}
