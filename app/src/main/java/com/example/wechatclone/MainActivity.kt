package com.example.wechatclone

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wechatclone.ui.MomentAdapter
import com.example.wechatclone.ui.MomentViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MomentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MomentViewModel::class.java)

//        val adapter = viewModel.profile.value?.let {
//            MomentAdapter(it, viewModel.tweets.value!!)
//        }
        val adapter = MomentAdapter()

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        viewModel.tweets.observe(this, Observer {
            adapter.refreshPage(viewModel.profile.value!!, it)
        })

        viewModel.getUserProfile()
        viewModel.getTweets()
    }
}
