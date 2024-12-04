package com.acmespectrum.postopia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.acmespectrum.postopia.data.response.post.Post
import com.acmespectrum.postopia.databinding.PostTileBinding

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    inner class PostsViewHolder(val binding: PostTileBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    var differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(
            PostTileBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val post = differ.currentList[position]

        holder.binding.tvPostUserName.text = post.user.name

        if (post.description.isNotEmpty()) {
            holder.binding.tvPostDescription.visibility = View.VISIBLE
            holder.binding.tvPostDescription.text = post.description
        }
        if (post.image.isNotEmpty()) {
            holder.binding.ivPostImage.visibility = View.VISIBLE
            Glide.with(holder.binding.ivPostImage).load(post.image).into(holder.binding.ivPostImage)
        }

    }
}