package com.example.wechatclone.ui

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SimpleAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.wechatclone.R
import com.example.wechatclone.data.Tweet.Url

class ImageGridViewAdapter(private val imageUrls: List<Url>, private val itemView: View) {

    private val imageSize = ImageUtil(itemView).getDynamicImageSize(imageUrls)

    fun getAdapter(): SimpleAdapter {
        val list = imageUrls.map { mapOf("ItemImage" to it.url) }
        val adapter =
            when (imageUrls.size) {
                1 -> SimpleAdapter(
                    itemView.context, list, R.layout.image_item_large,
                    arrayOf("ItemImage"), intArrayOf(R.id.imageViewLarge)
                )
                else -> SimpleAdapter(
                    itemView.context, list, R.layout.image_item,
                    arrayOf("ItemImage"), intArrayOf(R.id.imageView)
                )
            }

        adapter.viewBinder = SimpleAdapter.ViewBinder { view, data, _ ->
            if (view is ImageView) {
                view.layoutParams = LinearLayout.LayoutParams(imageSize, imageSize)
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
