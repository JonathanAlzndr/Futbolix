package com.example.futbolix.core.di

import androidx.room.Room
import com.example.futbolix.core.data.PlayerRepository
import com.example.futbolix.core.data.SettingPreferences
import com.example.futbolix.core.data.dataStore
import com.example.futbolix.core.data.local.PlayerRoomDatabase
import com.example.futbolix.core.data.network.retrofit.ApiService
import com.example.futbolix.core.domain.repository.IPlayerRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("player".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
           PlayerRoomDatabase::class.java, "player.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}
val networkModule = module {
    single {
        val hostName = "thesportsdb.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostName, "sha256/O0du+3OVqnSRoBd2S3eeTd6fsuQlKrPhh86ctwOoeu4=")
            .add(hostName, "sha256/i7LHCNm4KCjPzj1tqkk+ay+iOmDaEPHvFlQiOwufi8M=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
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
