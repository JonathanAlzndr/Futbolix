package com.example.futbolix.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.futbolix.core.domain.usecase.PlayerUseCase
import kotlinx.coroutines.launch

class SettingViewModel(val playerUseCase: PlayerUseCase) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return playerUseCase.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            playerUseCase.saveThemeSetting(isDarkModeActive)
        }
    }
}