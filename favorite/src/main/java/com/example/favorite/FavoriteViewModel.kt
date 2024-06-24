package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.usecase.PlayerUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {
    fun getAllFavoritePlayer() = playerUseCase.getAllFavoritePlayers().asLiveData()
    fun getFavoritePlayerByUsername(name: String) =
        playerUseCase.getFavoritePlayerByName(name).asLiveData()

    fun delete(player: PlayerModel) = viewModelScope.launch { playerUseCase.delete(player) }
    fun insert(player: PlayerModel) = viewModelScope.launch { playerUseCase.insert(player) }
}