package com.example.futbolix.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.usecase.PlayerUseCase

class FavoriteViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {

    fun getAllFavoritePlayer() = playerUseCase.getAllFavoritePlayers()

    fun getFavoritePlayerByUsername(name: String) = playerUseCase.getFavoritePlayerByName(name)
    fun delete(player: PlayerModel) = playerUseCase.delete(player)
    fun insert(player: PlayerModel) = playerUseCase.insert(player)
}