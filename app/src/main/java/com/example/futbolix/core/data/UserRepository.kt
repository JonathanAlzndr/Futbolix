package com.example.futbolix.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.futbolix.core.data.local.PlayerDao
import com.example.futbolix.core.data.local.PlayerEntity
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.data.network.response.ResponsePlayer
import com.example.futbolix.core.data.network.retrofit.ApiService
import com.example.futbolix.core.utils.AppExecutors
import com.example.futbolix.core.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(
    private val apiService: ApiService,
    private val playerDao: PlayerDao,
    private val appExecutors: AppExecutors
) {

    fun searchPlayer(playerName: String): LiveData<Result<List<PlayerItem>>> {
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

    fun getAllFavoritePlayers() = playerDao.getAllFavoritePlayers()

    fun insert(player: PlayerEntity) {
        appExecutors.diskIO.execute {
            playerDao.insert(player)
        }
    }

    fun delete(player: PlayerEntity) {
        appExecutors.diskIO.execute {
            playerDao.delete(player)
        }
    }

    fun getFavoritePlayerByName(name: String) = playerDao.getFavoriteByName(name)

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(apiService: ApiService, playerDao: PlayerDao, appExecutors: AppExecutors): UserRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = UserRepository(apiService, playerDao, appExecutors)
                }
            }
            return INSTANCE as UserRepository
        }

        private const val TAG = "UserRepository"
    }


}