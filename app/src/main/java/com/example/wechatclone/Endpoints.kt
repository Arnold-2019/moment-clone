package com.example.network

import com.example.wechatclone.data.Tweet
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {

    @GET("/user/jsmith/tweets")
    fun getTweets(): Call<List<Tweet>>
}