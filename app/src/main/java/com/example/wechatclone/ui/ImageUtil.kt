package com.example.wechatclone.ui

import android.content.res.Resources
import android.view.View
import com.example.wechatclone.R
import com.example.wechatclone.data.Tweet

class ImageUtil(private val itemView: View) {
    fun singleImageSize(): Int {
        val singleImageScreenRatio =
            itemView.context.resources.getInteger(R.integer.single_image_screen_ratio)
        return screenWidth() * singleImageScreenRatio / 100
    }

    fun multipleImagesSize(): Int {
        val multipleImageScreenRatio =
            itemView.context.resources.getInteger(R.integer.image_screen_ratio)
        return screenWidth() * multipleImageScreenRatio / 100
    }

    fun getDynamicImageSize(imageUrls: List<Tweet.Url>): Int {
        return if (imageUrls.size == 1) singleImageSize() else multipleImagesSize()
    }

    private fun screenWidth() = Resources.getSystem().displayMetrics.widthPixels
}
