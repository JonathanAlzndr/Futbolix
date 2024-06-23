package com.example.futbolix.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(player: PlayerEntity)

    @Delete
    suspend fun delete(player: PlayerEntity)

    @Query("SELECT * FROM PlayerEntity ORDER BY name ASC")
    fun getAllFavoritePlayers(): Flow<List<PlayerEntity>>

    @Query("SELECT * FROM PlayerEntity WHERE name = :name")
    fun getFavoriteByName(name: String): Flow<PlayerEntity>

}