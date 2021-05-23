package com.example.wechatclone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wechatclone.R
import com.example.wechatclone.data.Tweet.UserComment
import kotlinx.android.synthetic.main.comment_item.view.comment_text_view

class CommentAdapter(
        private val comments: List<UserComment>
) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.comment_item, parent, false)
        return CommentViewHolder(itemView)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        return holder.bind(comments[position])
    }

    class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: UserComment) {
            itemView.comment_text_view.text = comment.content
        }
    }
}
