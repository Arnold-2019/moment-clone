package com.example.wechatclone.ui

import android.view.View
import android.widget.ImageView
import android.widget.SimpleAdapter
import com.example.wechatclone.R
import com.example.wechatclone.data.Tweet.Url
import java.util.ArrayList

class GridViewAdapter(private val imageUrls: List<Url>, private val itemView: View) {

    fun getGridViewAdapter(): SimpleAdapter {
        // use map
        val list = ArrayList<HashMap<String, String?>>()
        imageUrls.forEach {
            val map = HashMap<String, String?>()
            map["ItemImage"] = it.url
            list.add(map)
        }

        val adapter = SimpleAdapter(itemView.context, list, R.layout.image_item, arrayOf("ItemImage"), intArrayOf(R.id.imageView))
        adapter.viewBinder = SimpleAdapter.ViewBinder { view, data, _ ->
            if (view is ImageView) {
                GlideUtil.glideWithPlaceHolder(itemView.context, data.toString()).into(view)
                true
            } else false
        }
        return adapter
    }
}
