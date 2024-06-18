package com.example.futbolix.core.di

import com.example.futbolix.core.data.network.retrofit.ApiConfig
import com.example.futbolix.core.data.UserRepository

object Injection {
    fun provideRepository(): UserRepository {
        val apiService = ApiConfig.provideService()
        return UserRepository.getInstance(apiService)
    }
}