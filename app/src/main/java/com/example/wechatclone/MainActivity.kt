package com.example.wechatclone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechatclone.ui.MomentAdapter
import com.example.wechatclone.ui.MomentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MomentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = MomentAdapter()
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        with(viewModel) {
            tweets.observe(this@MainActivity, Observer {
                adapter.refreshPage(viewModel.profile.value!!, it)
            })
            getTweets()
            getUserProfile()
        }
    }
}
