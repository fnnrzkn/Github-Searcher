package com.irfannurrizki.githubsearcher.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irfannurrizki.githubsearcher.alldata.api.GithubApiConfig
import com.irfannurrizki.githubsearcher.alldata.api.GithubApiResponse
import com.irfannurrizki.githubsearcher.alldata.api.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel(){
    private val _listGithubUser = MutableLiveData<List<ItemsItem>>()
    val listGithubUser: LiveData<List<ItemsItem>> = _listGithubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    companion object {
        private const val TAG = "MainViewModel"
        private const val USER = "user"
    }

    init {
        findGithubUser(USER)
    }

    fun findGithubUser(user: String) {
        _isLoading.value = true
        val client = GithubApiConfig.getApiService().findGithubUser(user)
        client.enqueue(object : Callback<GithubApiResponse> {
            override fun onResponse(
                call: Call<GithubApiResponse>,
                response: Response<GithubApiResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _listGithubUser.value = (response.body()?.items as List<ItemsItem>?)!!
                } else {
                    Log.e(TAG, "On Failure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubApiResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "On Failure: ${t.message}")
            }
        })
    }
}