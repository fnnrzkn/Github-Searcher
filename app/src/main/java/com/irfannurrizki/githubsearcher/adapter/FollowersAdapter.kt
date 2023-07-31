package com.irfannurrizki.githubsearcher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.irfannurrizki.githubsearcher.alldata.api.ItemsItem

class FollowersAdapter (private val listUser: List<ItemsItem>) : RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvUsername: TextView = view.findViewById(R.id.tv_username)
            val ivAvatar: ImageView = view.findViewById(R.id.iv_avataruser)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvUsername.text = listUser[position].login
            Glide.with(holder.itemView.context)
                .load(listUser[position].avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(holder.ivAvatar)
        }

        override fun getItemCount() = listUser.size

    }