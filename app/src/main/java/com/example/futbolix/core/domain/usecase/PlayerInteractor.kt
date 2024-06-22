package com.example.futbolix.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.repository.IPlayerRepository
import com.example.futbolix.core.utils.Result
import kotlinx.coroutines.flow.Flow

class PlayerInteractor(private val playerRepository: IPlayerRepository) : PlayerUseCase {
    override fun searchPlayer(playerName: String): LiveData<Result<List<PlayerItem>>> = playerRepository.searchPlayer(playerName)
    override fun getAllFavoritePlayers(): LiveData<List<PlayerModel>> = playerRepository.getAllFavoritePlayers()

    override fun insert(player: PlayerModel) = playerRepository.insert(player)

    override fun delete(player: PlayerModel) = playerRepository.delete(player)

    override fun getFavoritePlayerByName(name: String): LiveData<PlayerModel> = playerRepository.getFavoritePlayerByName(name)

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) = playerRepository.saveThemeSetting(isDarkModeActive)

    override fun getThemeSetting(): Flow<Boolean> = playerRepository.getThemeSetting()

}