package com.example.futbolix.ui.home

import androidx.lifecycle.ViewModel
import com.example.futbolix.ui.UserRepository

class HomeViewModel(private val userRepository: UserRepository) : ViewModel() {

    init {
        searchPlayer("Cristiano")
    }

    fun searchPlayer(playerName: String) = run { userRepository.searchPlayer(playerName) }
}