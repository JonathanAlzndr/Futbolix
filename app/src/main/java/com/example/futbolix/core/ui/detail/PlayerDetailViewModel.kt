package com.example.futbolix.ui.detail

import androidx.lifecycle.ViewModel
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.usecase.PlayerUseCase

class PlayerDetailViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {
    fun delete(player: PlayerModel) = playerUseCase.delete(player)
    fun insert(player: PlayerModel) = playerUseCase.insert(player)
}