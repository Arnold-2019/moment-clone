package com.example.wechatclone.data

import com.google.gson.annotations.SerializedName

data class UserProfile (
        @SerializedName("profile-image")
        val profileImage: String,
        val avatar: String,
        val nick: String,
        @SerializedName("username")
        val userName: String
)
