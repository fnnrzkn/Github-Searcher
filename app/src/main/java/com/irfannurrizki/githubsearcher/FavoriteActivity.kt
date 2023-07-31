package com.irfannurrizki.githubsearcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.ActivityFavoriteBinding
import com.irfannurrizki.githubsearcher.adapter.FavoriteUserAdapter
import com.irfannurrizki.githubsearcher.alldata.database.FavoriteUser
import com.irfannurrizki.githubsearcher.appsetting.ViewModelFactory
import com.irfannurrizki.githubsearcher.viewmodel.FavoriteUserViewModel

class FavoriteActivity : AppCompatActivity () {
        private var _binding: ActivityFavoriteBinding? = null
        private val binding get() = _binding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            _binding = ActivityFavoriteBinding.inflate(layoutInflater)
            setContentView(binding?.root)

            supportActionBar?.title = "Favorite User"

            val favoriteViewModel = obtainViewModel(this@FavoriteActivity)
            favoriteViewModel.getAllFavorites().observe(this) {
                setListFavoriteData(it)
            }
        }

        private fun setListFavoriteData(dataUser: List<FavoriteUser>) {
            val items = arrayListOf<FavoriteUser>()
            dataUser.map {
                val item = FavoriteUser(it.username ,it.avatarUrl)
                items.add(item)
            }
            val adapter = FavoriteUserAdapter(items)
            binding?.rvFav?.layoutManager = LinearLayoutManager(this)
            binding?.rvFav?.setHasFixedSize(true)
            binding?.rvFav?.adapter = adapter

            adapter.setOnItemClickCallback(object : FavoriteUserAdapter.OnItemClickCallback {
                override fun onItemClicked(data: FavoriteUser) {
                    startActivity(
                        Intent(this@FavoriteActivity, DetailActivity::class.java)
                            .putExtra(DetailActivity.USERNAME, data.username)
                            .putExtra(DetailActivity.AVATAR, data.avatarUrl)
                    )
                }

            })
        }

        private fun obtainViewModel(activity: AppCompatActivity): FavoriteUserViewModel {
            val factory = ViewModelFactory.getInstance(activity.application)
            return ViewModelProvider(activity, factory).get(FavoriteUserViewModel::class.java)
        }
    }