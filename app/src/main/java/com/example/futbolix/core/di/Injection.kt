package com.example.futbolix.core.di

import android.content.Context
import com.example.futbolix.core.data.UserRepository
import com.example.futbolix.core.data.local.PlayerRoomDatabase
import com.example.futbolix.core.data.network.retrofit.ApiConfig
import com.example.futbolix.core.utils.AppExecutors
import com.example.futbolix.core.utils.SettingPreferences
import com.example.futbolix.core.utils.dataStore

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.provideService()
        val database = PlayerRoomDatabase.getInstance(context)
        val dao = database.playerDao()
        val appExecutors = AppExecutors()
        val pref = context.dataStore
        val userSettingPreferences = SettingPreferences.getInstance(pref)
        return UserRepository.getInstance(apiService, dao, appExecutors, userSettingPreferences)
    }
}