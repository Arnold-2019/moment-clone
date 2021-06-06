package com.example.wechatclone.ui

import android.util.DisplayMetrics
import android.view.View
import com.example.wechatclone.data.Tweet

class ImageGridViewUtil(private val itemView: View) {
    private val oneColumn = 1
    private val twoColumns = 2
    private val threeColumns = 3
    private val inset = 5
    private val doubleInset = 10
    private val imageUtil = ImageUtil(itemView)
    private val singleImageSize = imageUtil.singleImageSize()
    private val multipleImageSize = imageUtil.multipleImagesSize()
    private val twoColumnOrRowSize = multipleImageSize * twoColumns + inset.toPx()
    private val threeColumnOrRowSize = multipleImageSize * threeColumns + doubleInset.toPx()

    fun getDynamicHeight(tweet: Tweet): Int {
        return when (tweet.images?.size) {
            1 -> singleImageSize
            in 1 until 4 -> multipleImageSize
            in 4 until 7 -> twoColumnOrRowSize
            else -> threeColumnOrRowSize
        }
    }

    fun getDynamicWidth(tweet: Tweet): Int {
        return when (tweet.images?.size) {
            1 -> singleImageSize
            2 -> twoColumnOrRowSize
            4 -> twoColumnOrRowSize
            else -> threeColumnOrRowSize
        }
    }

    fun getDynamicNumColumns(tweet: Tweet): Int {
        return when (tweet.images?.size) {
            1 -> oneColumn
            2 -> twoColumns
            4 -> twoColumns
            else -> threeColumns
        }
    }

    private fun Int.toPx() =
        this * itemView.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT
}
