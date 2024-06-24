package com.example.futbolix.core.domain.repository

import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.utils.Result
import kotlinx.coroutines.flow.Flow

interface IPlayerRepository {
    suspend fun searchPlayer(playerName: String) : Flow<Result<List<PlayerModel>>>
    fun getAllFavoritePlayers(): Flow<List<PlayerModel>>
    suspend fun insert(player: PlayerModel)
    suspend fun delete(player: PlayerModel)
    fun getFavoritePlayerByName(name: String): Flow<PlayerModel>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
    fun getThemeSetting(): Flow<Boolean>
}