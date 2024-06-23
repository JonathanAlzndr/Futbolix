package com.example.futbolix.core.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.usecase.PlayerUseCase
import com.example.futbolix.core.utils.Result


class HomeViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {

    init {
        searchPlayer("Cristiano")
    }

    fun searchPlayer(playerName: String )= liveData {
        emitSource(playerUseCase.searchPlayer(playerName).asLiveData())
    }
}