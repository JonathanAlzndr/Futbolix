package com.example.futbolix.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.usecase.PlayerUseCase
import kotlinx.coroutines.launch

class PlayerDetailViewModel(private val playerUseCase: PlayerUseCase) : ViewModel() {
    fun delete(player: PlayerModel) = viewModelScope.launch { playerUseCase.delete(player) }
    fun insert(player: PlayerModel) = viewModelScope.launch { playerUseCase.insert(player) }
}