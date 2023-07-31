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
import com.irfannurrizki.githubsearcher.alldata.database.FavoriteUser

class FavoriteUserAdapter (private val listFavoriteUser: List<FavoriteUser>) : RecyclerView.Adapter<FavoriteUserAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvUsername: TextView = view.findViewById(R.id.tv_username)
        val ivAvatar: ImageView = view.findViewById(R.id.iv_avataruser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvUsername.text = listFavoriteUser[position].username
        Glide.with(holder.itemView.context)
            .load(listFavoriteUser[position].avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.ivAvatar)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFavoriteUser[holder.adapterPosition])
        }
    }

    override fun getItemCount() = listFavoriteUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: FavoriteUser)
    }
}

