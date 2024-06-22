package com.example.futbolix.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.futbolix.core.data.local.PlayerDao
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.data.network.response.ResponsePlayer
import com.example.futbolix.core.data.network.retrofit.ApiService
import com.example.futbolix.core.domain.model.PlayerModel
import com.example.futbolix.core.domain.repository.IPlayerRepository
import com.example.futbolix.core.utils.AppExecutors
import com.example.futbolix.core.utils.DataMapper
import com.example.futbolix.core.utils.Result
import com.example.futbolix.core.utils.SettingPreferences
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayerRepository private constructor(
    private val apiService: ApiService,
    private val playerDao: PlayerDao,
    private val appExecutors: AppExecutors,
    private val userPref: SettingPreferences
) : IPlayerRepository {

    override fun searchPlayer(playerName: String): LiveData<Result<List<PlayerItem>>> {
        val result = MutableLiveData<Result<List<PlayerItem>>>()

        result.value = Result.Loading
        apiService.searchPlayer(playerName).enqueue(object : Callback<ResponsePlayer> {
            override fun onResponse(
                call: Call<ResponsePlayer>,
                response: Response<ResponsePlayer>
            ) {
                if (response.isSuccessful) {
                    if (response.body()?.player != null) {
                        result.value = Result.Success(response.body()?.player!!)
                    }
                } else {
                    result.value = Result.Error(response.message())
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponsePlayer>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }
        })

        return result
    }

    override fun getAllFavoritePlayers(): LiveData<List<PlayerModel>> {
        val result = MediatorLiveData<List<PlayerModel>>()
        result.addSource(playerDao.getAllFavoritePlayers()) {
            DataMapper.mapEntitiesToDomain(it)
        }
        return result
    }


    override fun insert(player: PlayerModel) {
        appExecutors.diskIO.execute {
            playerDao.insert(DataMapper.mapDomainToEntity(player))
        }
    }

    override fun delete(player: PlayerModel) {
        appExecutors.diskIO.execute {
            playerDao.delete(DataMapper.mapDomainToEntity(player))
        }
    }

    override fun getFavoritePlayerByName(name: String): LiveData<PlayerModel> {
        val result = MediatorLiveData<PlayerModel>()
        result.addSource(playerDao.getFavoriteByName(name)) {
            DataMapper.mapEntityToDomain(it)
        }
        return result
    }

    override suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        userPref.saveThemeSetting(isDarkModeActive)
    }

    override fun getThemeSetting(): Flow<Boolean> {
        return userPref.getThemeSetting()
    }

    companion object {
        @Volatile
        private var INSTANCE: PlayerRepository? = null

        fun getInstance(apiService: ApiService, playerDao: PlayerDao, appExecutors: AppExecutors, settingPref: SettingPreferences): PlayerRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = PlayerRepository(apiService, playerDao, appExecutors, settingPref)
                }
            }
            return INSTANCE as PlayerRepository
        }

        private const val TAG = "UserRepository"
    }


}