package com.irfannurrizki.githubsearcher.alldata.database

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteUserRepository (application: Application) {
    private val mFavoriteUserDao: FavoriteUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteUserDatabase.getDatabase(application)
        mFavoriteUserDao = db.favoriteUserDao()
    }

    fun getAllFavoriteUser(): LiveData<List<FavoriteUser>> = mFavoriteUserDao.getAllFavUser()


    fun getFavoriteUser(username: String): LiveData<List<FavoriteUser>> =
        mFavoriteUserDao.getFavoriteUser(username)

    fun insertFavoriteUser(favoriteUser: FavoriteUser) {
        executorService.execute {
            mFavoriteUserDao.insert(favoriteUser)
        }
    }

    fun deleteFavoriteUser(favoriteUser: FavoriteUser) {
        executorService.execute {
            mFavoriteUserDao.delete(favoriteUser)
        }
    }
}