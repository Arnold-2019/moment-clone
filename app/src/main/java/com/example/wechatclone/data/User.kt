package com.example.wechatclone.data

import com.google.gson.annotations.SerializedName

data class User (
//        @SerializedName("username")
        val username: String,
        val nick: String,
        val avatar: String
)
