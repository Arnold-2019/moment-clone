package com.example.wechatclone.ui

import android.content.res.Resources
import android.view.View
import com.example.wechatclone.R
import com.example.wechatclone.data.Tweet

class ImageSizeHelper(private val itemView: View) {

    fun getSingleImageSize(): Int {
        val gridViewMarginStart = itemView.resources.getDimensionPixelSize(R.dimen.grid_view_margin)
        val gridViewMarginEnd = itemView.resources.getDimensionPixelSize(R.dimen.grid_view_margin_end_large)
        return screenWidth() - gridViewMarginStart - gridViewMarginEnd
    }

    fun getMultipleImageSize(): Int {
        val gridViewMargin = itemView.resources.getDimensionPixelSize(R.dimen.grid_view_margin)
        val gridViewWidth = screenWidth() - gridViewMargin * 2
        return gridViewWidth / 3
    }

    fun getDynamicImageSize(imageUrls: List<Tweet.Url>): Int {
        return if (imageUrls.size == 1) getSingleImageSize() else getMultipleImageSize()
    }

    private fun screenWidth() = Resources.getSystem().displayMetrics.widthPixels
}
