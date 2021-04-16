package com.example.wechatclone

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    // receive UserComment
    fun bind(comment: String) {
        itemView.comment_text_view.text = comment
    }

}