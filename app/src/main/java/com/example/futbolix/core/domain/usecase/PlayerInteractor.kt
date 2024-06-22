package com.example.futbolix.core.domain.usecase

import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.repository.IPlayerRepository
import kotlinx.coroutines.flow.Flow

class PlayerInteractor(private val playerRepository: IPlayerRepository) : PlayerUseCase {
    override fun searchPlayer(playerName: String) = playerRepository.searchPlayer(playerName)
    override fun getAllFavoritePlayers() = playerRepository.getAllFavoritePlayers()

    override fun insert(player: PlayerModel) = playerRepository.insert(player)

    override fun delete(player: PlayerModel) = playerRepository.delete(player)

    override fun getFavoritePlayerByName(name: String) = playerRepository.getFavoritePlayerByName(name)

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) = playerRepository.saveThemeSetting(isDarkModeActive)

    override fun getThemeSetting(): Flow<Boolean> = playerRepository.getThemeSetting()

}