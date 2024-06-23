package com.example.futbolix.ui.home

import androidx.lifecycle.ViewModel
import com.example.futbolix.core.domain.usecase.PlayerUseCase

class HomeViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {

    init {
        searchPlayer("Cristiano")
    }

    fun searchPlayer(playerName: String) = run { playerUseCase.searchPlayer(playerName) }
}