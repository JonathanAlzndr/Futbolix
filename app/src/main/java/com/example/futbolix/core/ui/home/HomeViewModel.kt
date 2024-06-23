package com.example.futbolix.core.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.usecase.PlayerUseCase
import com.example.futbolix.core.utils.Result

class HomeViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {

    init {
        searchPlayer("Cristiano")
    }

    fun searchPlayer(playerName: String): LiveData<Result<List<PlayerModel>>> = liveData {
        emit(Result.Loading)
        try {
            playerUseCase.searchPlayer(playerName).collect {
                emit(it)
            }
        }catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}