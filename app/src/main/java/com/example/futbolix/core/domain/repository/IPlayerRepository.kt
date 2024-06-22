package com.example.futbolix.core.domain.repository

import androidx.lifecycle.LiveData
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.utils.Result
import kotlinx.coroutines.flow.Flow

interface IPlayerRepository {
    fun searchPlayer(playerName: String) : LiveData<Result<List<PlayerItem>>>
    fun getAllFavoritePlayers(): LiveData<List<PlayerModel>>
    fun insert(player: PlayerModel)
    fun delete(player: PlayerModel)
    fun getFavoritePlayerByName(name: String): LiveData<PlayerModel>
    suspend fun saveThemeSetting(isDarkModeActive: Boolean)
    fun getThemeSetting(): Flow<Boolean>
}