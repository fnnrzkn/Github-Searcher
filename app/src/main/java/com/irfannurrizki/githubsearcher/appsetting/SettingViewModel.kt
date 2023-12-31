package com.irfannurrizki.githubsearcher.appsetting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SettingViewModel (private val settingPreferences: SettingPreferences) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return settingPreferences.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            settingPreferences.saveThemeSetting(isDarkModeActive)
        }
    }
}