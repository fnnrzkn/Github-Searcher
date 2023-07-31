package com.irfannurrizki.githubsearcher.alldata.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteUserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteuser: FavoriteUser)

    @Delete
    fun delete(favoriteuser: FavoriteUser)

    @Query("SELECT * from favorite_user_database ORDER BY username ASC")
    fun getAllFavUser(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM favorite_user_database WHERE username = :username")
    fun getFavoriteUser(username: String): LiveData<List<FavoriteUser>>
}