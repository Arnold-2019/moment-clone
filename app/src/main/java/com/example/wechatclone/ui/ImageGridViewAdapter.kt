package com.example.wechatclone.ui

import android.view.View
import android.widget.ImageView
import android.widget.SimpleAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.wechatclone.R
import com.example.wechatclone.data.Tweet.Url

class ImageGridViewAdapter(private val imageUrls: List<Url>, private val itemView: View) {

    fun getAdapter(): SimpleAdapter {
        val list2 = imageUrls.map { mapOf("ItemImage" to it.url) }
        val adapter = SimpleAdapter(itemView.context, list2, R.layout.image_item, arrayOf("ItemImage"), intArrayOf(R.id.imageView))
        adapter.viewBinder = SimpleAdapter.ViewBinder { view, data, _ ->
            if (view is ImageView) {
                Glide.with(itemView.context)
                        .load(data.toString())
                        .apply(
                                RequestOptions()
                                        .placeholder(android.R.drawable.ic_menu_camera)
                                        .dontAnimate()
                        ).into(view)

                true
            } else false
        }
        return adapter
    }
}
