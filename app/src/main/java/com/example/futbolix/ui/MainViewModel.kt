package com.example.futbolix.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.futbolix.core.domain.usecase.PlayerUseCase

class MainViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return playerUseCase.getThemeSetting().asLiveData()
    }


}