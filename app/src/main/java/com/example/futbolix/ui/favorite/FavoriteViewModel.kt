package com.example.futbolix.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.futbolix.core.data.UserRepository
import com.example.futbolix.core.data.local.PlayerEntity

class FavoriteViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun getAllFavoritePlayer() = userRepository.getAllFavoritePlayers()

    fun getFavoritePlayerByUsername(name: String) = userRepository.getFavoritePlayerByName(name)
    fun delete(player: PlayerEntity) = userRepository.delete(player)
    fun insert(player: PlayerEntity) = userRepository.insert(player)
}