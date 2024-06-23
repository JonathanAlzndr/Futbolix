package com.example.futbolix.core.di

import android.content.Context
import com.example.futbolix.core.data.PlayerRepository
import com.example.futbolix.core.data.SettingPreferences
import com.example.futbolix.core.data.dataStore
import com.example.futbolix.core.data.local.PlayerRoomDatabase
import com.example.futbolix.core.data.network.retrofit.ApiConfig
import com.example.futbolix.core.domain.repository.IPlayerRepository
import com.example.futbolix.core.domain.usecase.PlayerInteractor
import com.example.futbolix.core.domain.usecase.PlayerUseCase

object Injection {
    private fun provideRepository(context: Context): IPlayerRepository {
        val apiService = ApiConfig.provideService()
        val database = PlayerRoomDatabase.getInstance(context)
        val dao = database.playerDao()
        val pref = context.dataStore
        val userSettingPreferences = SettingPreferences.getInstance(pref)
        return PlayerRepository.getInstance(apiService, dao, userSettingPreferences)
    }

    fun providePlayerUseCase(context: Context): PlayerUseCase {
        val repository = provideRepository(context)
        return PlayerInteractor(repository)
    }
}