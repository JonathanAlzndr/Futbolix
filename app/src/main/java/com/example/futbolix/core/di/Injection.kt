package com.example.futbolix.core.di

import android.content.Context
import com.example.futbolix.core.data.UserRepository
import com.example.futbolix.core.data.local.PlayerRoomDatabase
import com.example.futbolix.core.data.network.retrofit.ApiConfig
import com.example.futbolix.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.provideService()
        val database = PlayerRoomDatabase.getInstance(context)
        val dao = database.playerDao()
        val appExecutors = AppExecutors()
        return UserRepository.getInstance(apiService, dao, appExecutors)
    }
}