package com.example.futbolix.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [PlayerEntity::class], version = 1, exportSchema = false)
abstract class PlayerRoomDatabase : RoomDatabase() {

    abstract fun playerDao(): PlayerDao

}