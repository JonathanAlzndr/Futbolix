package com.example.futbolix.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.futbolix.core.data.network.response.PlayerItem
import com.example.futbolix.core.data.network.response.ResponsePlayer
import com.example.futbolix.core.data.network.retrofit.ApiService
import com.example.futbolix.core.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository private constructor(private val apiService: ApiService) {

    fun searchPlayer(playerName: String): LiveData<Result<List<PlayerItem>>> {
        val result = MutableLiveData<Result<List<PlayerItem>>>()

        result.value = Result.Loading
        apiService.searchPlayer(playerName).enqueue(object: Callback<ResponsePlayer> {
            override fun onResponse(
                call: Call<ResponsePlayer>,
                response: Response<ResponsePlayer>
            ) {
                if(response.isSuccessful) {
                    if(response.body()?.player != null) {
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

    companion object{
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(apiService: ApiService): UserRepository {
            if(INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = UserRepository(apiService)
                }
            }
            return INSTANCE as UserRepository
        }

        private const val TAG = "UserRepository"
    }


}