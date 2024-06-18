package com.example.futbolix.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.futbolix.core.data.UserRepository

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return userRepository.getThemeSetting().asLiveData()
    }


}