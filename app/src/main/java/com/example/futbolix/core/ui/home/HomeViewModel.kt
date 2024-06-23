package com.example.futbolix.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.futbolix.core.domain.usecase.PlayerUseCase

class HomeViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {

    init {
        searchPlayer("Cristiano")
    }

    fun searchPlayer(playerName: String)= liveData {
        emitSource(playerUseCase.searchPlayer(playerName).asLiveData())
    }

}