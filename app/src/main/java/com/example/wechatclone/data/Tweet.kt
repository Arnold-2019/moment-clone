package com.example.wechatclone.data

import com.google.gson.annotations.SerializedName

data class Tweet (
    val content: String,
    val images: List<Url>,
    val sender: User,
    val comments: List<UserComment>,
    val error: String,
    @SerializedName("unknown error")
    val unknownError: String
)