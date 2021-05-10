package com.example.wechatclone

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.Endpoints
import com.example.network.ServiceBuilder
import com.example.wechatclone.data.Tweet
import com.example.wechatclone.data.UserProfile
import kotlinx.android.synthetic.main.activity_main.recycler_view
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getTweets()
        val callUserProfile = request.getProfile()

        var userProfile: UserProfile? = null

        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {

                if (response.isSuccessful) {
                    val tweets = response.body()

                    if (tweets != null) {
                        val validTweets = mutableListOf<Tweet>()

                        // use filter
                        tweets.forEach {
                            if (it.sender != null && (it.content != null || it.images != null)) {
                                validTweets.add(it)
                            }
                        }

                        recycler_view.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = userProfile?.let { TweetAdapter(it, validTweets) }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        callUserProfile.enqueue(object : Callback<UserProfile> {
            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
                if (response.isSuccessful) {
                    userProfile = response.body()
                }
            }
        })
    }
}
