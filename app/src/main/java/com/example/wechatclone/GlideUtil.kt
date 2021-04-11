package com.example.wechatclone

import android.R
import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions


object GlideUtil {

    fun glideWithPlaceHolder(context: Context?, `object`: Any?): RequestBuilder<Drawable> {
        return Glide
                .with(context!!)
                .load(`object`)
                .apply(RequestOptions().placeholder(R.drawable.ic_menu_camera).dontAnimate())
    }

}
