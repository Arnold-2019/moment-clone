package com.example.network

import com.example.wechatclone.data.AllTweets
import com.example.wechatclone.data.Tweet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Endpoints {

    @GET("/user/jsmith/tweets")
    fun getTweets(): Call<List<Tweet>>
}