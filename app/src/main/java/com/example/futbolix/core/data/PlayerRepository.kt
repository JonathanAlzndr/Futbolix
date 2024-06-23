package com.example.futbolix.core.data

import android.util.Log
import com.example.futbolix.core.data.local.PlayerDao
import com.example.futbolix.core.data.network.retrofit.ApiService
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.repository.IPlayerRepository
import com.example.futbolix.core.utils.DataMapper
import com.example.futbolix.core.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class PlayerRepository private constructor(
    private val apiService: ApiService,
    private val playerDao: PlayerDao,
    private val userPref: SettingPreferences
) : IPlayerRepository {

    override suspend fun searchPlayer(playerName: String): Flow<Result<List<PlayerModel>>> {
        return flow {
            try {
                emit(Result.Loading)
                val response = apiService.searchPlayer(playerName)
                val playerItemResponse = response.player
                if (playerItemResponse.isNotEmpty()) {
                    emit(Result.Success(DataMapper.mapPlayerItemToDomain(playerItemResponse)))
                } else {
                    emit(Result.Error("Player is not found"))
                }
            } catch (e: Exception) {
                emit(Result.Error(e.toString()))
                Log.e(TAG, "searchPlayer: $e")
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getAllFavoritePlayers(): Flow<List<PlayerModel>> {
        return playerDao.getAllFavoritePlayers().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override suspend fun insert(player: PlayerModel) {
        playerDao.insert(DataMapper.mapDomainToEntity(player))
        Log.d(TAG, "insert: Inserted player ${player.name}")
    }

    override suspend fun delete(player: PlayerModel) {
        playerDao.delete(DataMapper.mapDomainToEntity(player))
        Log.d(TAG, "delete: Deleted player ${player.name}")
    }

    override fun getFavoritePlayerByName(name: String): Flow<PlayerModel> {
        return playerDao.getFavoriteByName(name).map {
            if(it != null) {
                DataMapper.mapEntityToDomain(it)
            }
            else PlayerModel()
        }
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        userPref.saveThemeSetting(isDarkModeActive)
    }

    override fun getThemeSetting(): Flow<Boolean> {
        return userPref.getThemeSetting()
    }

    companion object {
        @Volatile
        private var INSTANCE: PlayerRepository? = null

        fun getInstance(
            apiService: ApiService,
            playerDao: PlayerDao,
            settingPref: SettingPreferences
        ): PlayerRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = PlayerRepository(apiService, playerDao, settingPref)
                }
            }
            return INSTANCE as PlayerRepository
        }

        private const val TAG = "UserRepository"
    }


}