package com.example.futbolix.ui.detail

import androidx.lifecycle.ViewModel
import com.example.futbolix.core.data.PlayerRepository
import com.example.futbolix.core.domain.model.PlayerModel

class PlayerDetailViewModel(private val player) : ViewModel() {
    fun delete(player: PlayerModel) = playerRepository.delete(player)
    fun insert(player: PlayerModel) = playerRepository.insert(player)
}