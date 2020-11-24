package com.example.mycleanarchitecture.framework.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mycleanarchitecture.framework.database.dao.SpaceDao
import com.example.mycleanarchitecture.framework.database.model.SpaceEntity

@Database(entities = [SpaceEntity::class], version = 1, exportSchema = false)
abstract class SpaceDatabase : RoomDatabase() {
    abstract fun spaceDao(): SpaceDao
}