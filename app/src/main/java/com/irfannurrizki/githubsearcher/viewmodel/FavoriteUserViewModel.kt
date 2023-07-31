package com.irfannurrizki.githubsearcher.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.irfannurrizki.githubsearcher.alldata.database.FavoriteUser
import com.irfannurrizki.githubsearcher.alldata.database.FavoriteUserRepository

class FavoriteUserViewModel (application: Application) : ViewModel() {

    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)
    fun getAllFavorites(): LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getAllFavoriteUser()

}