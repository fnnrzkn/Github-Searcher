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

class DetailActivityViewModel : ViewModel () {
    private val _detailUser = MutableLiveData<ItemsItem>()
    val detailUser: LiveData<ItemsItem> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object{
        private const val TAG = "DetailViewModel"
    }

    fun getDetail(data: String) {
        _isLoading.value = true
        val client = GithubApiConfig.getApiService().detailGithubUser(data)
        client.enqueue(object : Callback<ItemsItem> {
            override fun onResponse(call: Call<ItemsItem>, response: Response<ItemsItem>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _detailUser.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ItemsItem>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}
