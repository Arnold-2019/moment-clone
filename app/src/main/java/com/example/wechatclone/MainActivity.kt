package com.example.wechatclone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wechatclone.ui.MomentRecyclerViewAdapter
import com.example.wechatclone.ui.MomentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel by viewModels<MomentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        val adapter = MomentRecyclerViewAdapter()
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

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getUserProfile()
            viewModel.getTweets()
            adapter.refreshPage(viewModel.profile.value!!, viewModel.tweets.value!!)
            swipeRefreshLayout.isRefreshing = false
        }
    }
}
