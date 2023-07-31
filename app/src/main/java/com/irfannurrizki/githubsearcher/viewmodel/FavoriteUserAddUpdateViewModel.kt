package com.irfannurrizki.githubsearcher.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.irfannurrizki.githubsearcher.alldata.database.FavoriteUser
import com.irfannurrizki.githubsearcher.alldata.database.FavoriteUserRepository

class FavoriteUserAddUpdateViewModel (application: Application) : ViewModel() {

    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    fun getFavoritedUser(username: String) = mFavoriteUserRepository.getFavoriteUser(username)

    fun insertFavoriteUser(user: FavoriteUser) {
        mFavoriteUserRepository.insertFavoriteUser(user)
    }

    fun deleteFavoriteUser(user: FavoriteUser) {
        mFavoriteUserRepository.deleteFavoriteUser(user)
    }
}