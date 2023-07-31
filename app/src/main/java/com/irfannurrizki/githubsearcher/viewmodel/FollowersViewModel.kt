package com.irfannurrizki.githubsearcher.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfannurrizki.githubsearcher.alldata.api.GithubApiConfig
import com.irfannurrizki.githubsearcher.alldata.api.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel () {
    private val _followersUser = MutableLiveData<List<ItemsItem>>()
    val followerslUser: LiveData<List<ItemsItem>> = _followersUser
    private val _followingUser = MutableLiveData<List<ItemsItem>>()
    val followinglUser: LiveData<List<ItemsItem>> = _followingUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "FollowViewModel"
    }

    fun getUserFollowers(query: String = "") {
        _isLoading.value = true
        val client = GithubApiConfig.getApiService().findFollowersGithubUser(query)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followersUser.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getUserFollowing(data: String = "") {
        _isLoading.value = true
        val client = GithubApiConfig.getApiService().findFollowingGithubUser(data)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _followingUser.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
