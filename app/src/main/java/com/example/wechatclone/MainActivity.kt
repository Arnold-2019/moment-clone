package com.example.wechatclone

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.network.Endpoints
import com.example.network.ServiceBuilder
import com.example.wechatclone.data.AllTweets
import com.example.wechatclone.data.Tweet
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ServiceBuilder.buildService(Endpoints::class.java)
        val call = request.getTweets()

        call.enqueue(object : Callback<List<Tweet>> {
            override fun onResponse(call: Call<List<Tweet> >, response: Response<List<Tweet>>) {
                if (response.isSuccessful) {
                    Log.d("ARNOLD", "onResponse: http request succeed!")
                    val tweets = response.body()

                    recycler_view.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = tweets?.let { TweetAdapter(it) } ?: TweetAdapter(emptyList())
                    }
                }
            }

            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                Log.d("ARNOLD", "onFailure: http request failed!")
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}