package com.example.futbolix.core.data.network.retrofit

import com.example.futbolix.core.data.network.response.ResponsePlayer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("searchplayers.php")
    fun searchPlayer(@Query("p") playerName: String): Call<ResponsePlayer>
}