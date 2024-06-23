package com.example.futbolix.di

import com.example.futbolix.core.domain.usecase.PlayerInteractor
import com.example.futbolix.core.domain.usecase.PlayerUseCase
import com.example.futbolix.detail.PlayerDetailViewModel
import com.example.futbolix.favorite.FavoriteViewModel
import com.example.futbolix.home.HomeViewModel
import com.example.futbolix.main.MainViewModel
import com.example.futbolix.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PlayerUseCase> {
        PlayerInteractor(get())
    }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { SettingViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { PlayerDetailViewModel(get()) }
    viewModel { MainViewModel(get()) }
}
