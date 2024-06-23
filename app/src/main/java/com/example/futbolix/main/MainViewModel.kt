package com.example.futbolix.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.futbolix.core.domain.usecase.PlayerUseCase

class MainViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {
    fun getThemeSettings() = playerUseCase.getThemeSetting().asLiveData()

}