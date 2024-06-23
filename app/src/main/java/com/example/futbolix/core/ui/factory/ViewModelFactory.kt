package com.example.futbolix.ui.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.futbolix.core.di.Injection
import com.example.futbolix.core.domain.usecase.PlayerUseCase
import com.example.futbolix.ui.MainViewModel
import com.example.futbolix.ui.detail.PlayerDetailViewModel
import com.example.futbolix.ui.favorite.FavoriteViewModel
import com.example.futbolix.ui.home.HomeViewModel
import com.example.futbolix.ui.setting.SettingViewModel

class ViewModelFactory private constructor(private val playerUseCase: PlayerUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(playerUseCase) as T
        }
        if(modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(playerUseCase) as T
        }
        if(modelClass.isAssignableFrom(PlayerDetailViewModel::class.java)) {
            return PlayerDetailViewModel(playerUseCase) as T
        }
        if(modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(playerUseCase) as T
        }
        if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(playerUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.providePlayerUseCase(context))
            }.also { instance = it }
    }

}