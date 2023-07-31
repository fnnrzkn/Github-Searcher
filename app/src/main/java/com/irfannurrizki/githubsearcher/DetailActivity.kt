package com.irfannurrizki.githubsearcher

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubuser.R
import com.example.githubuser.databinding.ActivityDetailBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.irfannurrizki.githubsearcher.adapter.SectionPagerAdapter
import com.irfannurrizki.githubsearcher.alldata.api.ItemsItem
import com.irfannurrizki.githubsearcher.alldata.database.FavoriteUser
import com.irfannurrizki.githubsearcher.appsetting.ViewModelFactory
import com.irfannurrizki.githubsearcher.viewmodel.DetailActivityViewModel
import com.irfannurrizki.githubsearcher.viewmodel.FavoriteUserAddUpdateViewModel

class DetailActivity : AppCompatActivity () {
    private lateinit var binding: ActivityDetailBinding
    private val detailActivityViewModel by viewModels<DetailActivityViewModel>()
    private var isFavorite = false

    private lateinit var favoriteUserAddUpdateViewModel: FavoriteUserAddUpdateViewModel

    companion object {
        const val USERNAME = "username"
        const val AVATAR = "avatar"

        @StringRes
        private val TAB_TITTLES = intArrayOf(
            R.string.tab_page_followers,
            R.string.tab_page_following
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.extras?.get(USERNAME) as String
        val avatar = intent.extras?.get(AVATAR) as String

        detailActivityViewModel.detailUser.observe(this@DetailActivity) {
            getDetailUser(it)
        }
        detailActivityViewModel.isLoading.observe(this@DetailActivity) {
            showLoading(it)
        }
        detailActivityViewModel.getDetail(username)


        val sectionsPagerAdapter = SectionPagerAdapter (this)

        sectionsPagerAdapter.username = username

        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITTLES[position])
        }.attach()


        favoriteUserAddUpdateViewModel = obtainViewModel(this@DetailActivity)

        favoriteUserAddUpdateViewModel.getFavoritedUser(username).observe(this) {
            isFavorite = it.isNotEmpty()
            val favoriteUserEntity = FavoriteUser (username,  avatar)
            if (it.isEmpty()) {
                binding.favoriteUsertoogle.setImageDrawable(ContextCompat.getDrawable(binding.favoriteUsertoogle.context, R.drawable.ic_fav_hearth_border))
            } else {
                binding.favoriteUsertoogle.setImageDrawable(ContextCompat.getDrawable(binding.favoriteUsertoogle.context, R.drawable.ic_fav_hearth))
            }

            binding.favoriteUsertoogle.setOnClickListener {
                if (isFavorite) {
                    favoriteUserAddUpdateViewModel.deleteFavoriteUser(favoriteUserEntity)
                } else {
                    favoriteUserAddUpdateViewModel.insertFavoriteUser(favoriteUserEntity)
                }
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): FavoriteUserAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(FavoriteUserAddUpdateViewModel::class.java)
    }

    private fun getDetailUser(itemUser: ItemsItem?) {
        binding.apply {
            tvName.text = itemUser?.login
            tvFollowers.text =itemUser?.followers.toString()
            tvFollowing.text=itemUser?.following.toString()

            Glide.with(this@DetailActivity)
                .load(itemUser?.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .into(ivProfile)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBardetail.visibility = View.VISIBLE
        } else {
            binding.progressBardetail.visibility = View.GONE
        }
    }
}