package com.example.wechatclone

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.wechatclone.ui.MomentRecyclerViewAdapter
import com.example.wechatclone.ui.MomentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val viewModel by viewModels<MomentViewModel>()
    private val scrollingDown = 1

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
            profile.observe(this@MainActivity, Observer {
                adapter.refreshPage(it, viewModel.tweets.value!!)
            })
            getTweets()
            getUserProfile()
            loadMoreTweets()
        }

        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recycler_view.canScrollVertically(scrollingDown)) {
                    viewModel.loadMoreTweets()
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshLoadList()
            adapter.refreshPage(viewModel.profile.value!!, viewModel.tweets.value!!)
            swipeRefreshLayout.isRefreshing = false
        }
    }
}
