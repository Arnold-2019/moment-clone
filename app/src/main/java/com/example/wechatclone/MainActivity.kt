package com.example.wechatclone

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechatclone.data.MomentRepository
import com.example.wechatclone.data.Tweet
import com.example.wechatclone.data.UserProfile
import com.example.wechatclone.network.Endpoints
import com.example.wechatclone.network.ServiceBuilder
import com.example.wechatclone.ui.MomentAdapter
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

        val userProfile: UserProfile? = MomentRepository().searchUserProfile()


        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {

                if (response.isSuccessful) {
                    val tweets = response.body()

                    if (tweets != null) {
                        val validTweets = tweets.filter {
                            with(it) { sender != null && (content != null || images != null) }
                        }

                        recycler_view.apply {
                            val manager = LinearLayoutManager(context)
                            layoutManager = manager
                            adapter = userProfile?.let {
                                MomentAdapter(it, validTweets)
                            }
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

//        callUserProfile.enqueue(object : Callback<UserProfile> {
//            override fun onResponse(call: Call<UserProfile>, response: Response<UserProfile>) {
//                if (response.isSuccessful) {
//                    userProfile = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<UserProfile>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })

    }
}
