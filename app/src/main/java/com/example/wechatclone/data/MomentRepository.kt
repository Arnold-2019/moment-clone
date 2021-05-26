package com.example.wechatclone.data

import android.util.Log
import com.example.wechatclone.api.MomentApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread

@Singleton
class MomentRepository @Inject constructor(private val momentApi: MomentApi) {
    private val logTAG = "MomentRepository"

    fun searchTweets(callback: (tweets: List<Tweet>) -> Unit) {
        thread {
            momentApi.getTweets().enqueue(object : Callback<List<Tweet>> {
                override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                    if (response.isSuccessful) {
                        response.body()?.let { responseResults ->
                            callback(responseResults.filter {
                                with(it) { sender != null && (content != null || images != null) }
                            })
                        }
                    }
                }

                override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                    Log.e(logTAG, "onFailure: request for tweets failed!", t)
                }
            })
        }
    }

    fun searchUserProfile(callback: (userProfile: UserProfile) -> Unit) {
        thread {
            momentApi.getProfile().enqueue(object : Callback<UserProfile> {
                override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                    if (response.isSuccessful) {
                        response.body()?.let { callback(it) }
                    }
                }

                override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                    Log.e(logTAG, "onFailure: request for UserProfile failed!", t)
                }
            })
        }
    }
}
