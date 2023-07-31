package com.irfannurrizki.githubsearcher.alldata.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Favorite_User_Database")
@Parcelize
data class FavoriteUser(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "username")
        var username: String,

        @ColumnInfo(name = "avatarUrl")
        var avatarUrl: String,
    ) : Parcelable