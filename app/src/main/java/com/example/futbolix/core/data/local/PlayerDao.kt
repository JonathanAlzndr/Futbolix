package com.example.futbolix.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: PlayerEntity)

    @Delete
    fun delete(player: PlayerEntity)

    @Query("SELECT * FROM PlayerEntity ORDER BY name ASC")
    fun getAllFavoritePlayers(): LiveData<List<PlayerEntity>>

    @Query("SELECT * FROM PlayerEntity WHERE name = :name")
    fun getFavoriteByName(name: String): LiveData<PlayerEntity>

}