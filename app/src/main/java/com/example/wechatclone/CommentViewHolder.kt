package com.example.wechatclone

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.wechatclone.data.UserComment
import kotlinx.android.synthetic.main.comment_item.view.comment_text_view

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(comment: UserComment) {
        itemView.comment_text_view.text = comment.content
    }
}
