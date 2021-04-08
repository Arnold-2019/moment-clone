package com.example.wechatclone.data

data class Tweet (
        val content: String,
        val images: List<Url>,
        val sender: User,
        val comments: List<UserComment>
)