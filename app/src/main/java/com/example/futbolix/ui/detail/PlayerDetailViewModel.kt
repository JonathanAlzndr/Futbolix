package com.example.futbolix.ui.detail

import androidx.lifecycle.ViewModel
import com.example.futbolix.core.data.UserRepository
import com.example.futbolix.core.data.local.PlayerEntity

class PlayerDetailViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getFavoritePlayerByUsername(name: String) = userRepository.getFavoritePlayerByName(name)
    fun delete(player: PlayerEntity) = userRepository.delete(player)
    fun insert(player: PlayerEntity) = userRepository.insert(player)
}