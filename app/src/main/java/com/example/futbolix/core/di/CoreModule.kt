package com.example.futbolix.core.di

import androidx.room.Room
import com.example.futbolix.core.data.PlayerRepository
import com.example.futbolix.core.data.SettingPreferences
import com.example.futbolix.core.data.dataStore
import com.example.futbolix.core.data.local.PlayerRoomDatabase
import com.example.futbolix.core.data.network.retrofit.ApiService
import com.example.futbolix.core.domain.repository.IPlayerRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val databaseModule = module {
    factory { get<PlayerRoomDatabase>().playerDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            PlayerRoomDatabase::class.java,
            "player_database"
        ).fallbackToDestructiveMigration().build()
    }
}
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/api/v1/json/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single<IPlayerRepository> {
        PlayerRepository(
            apiService = get(),
            playerDao = get(),
            userPref = get()
        )
    }
    single { SettingPreferences(dataStore = get()) }
    single { androidContext().dataStore }
}
