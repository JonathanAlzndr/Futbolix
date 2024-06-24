package com.example.futbolix.core.data.network.retrofit

import com.example.futbolix.core.data.network.response.ResponsePlayer
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("searchplayers.php")
    suspend fun searchPlayer(@Query("p") playerName: String): ResponsePlayer
}