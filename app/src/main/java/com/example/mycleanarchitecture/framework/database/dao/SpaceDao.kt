package com.example.mycleanarchitecture.framework.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mycleanarchitecture.framework.database.model.SpaceEntity

@Dao
interface SpaceDao {
    @Query("SELECT * FROM spaces")
    fun selectAllLaunches() : List<SpaceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg spaceX: SpaceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(spaceXList: List<SpaceEntity>)

    @Query("SELECT * FROM spaces WHERE flightNumber=:flightNumber")
    fun selectLaunchByFlightNumber(flightNumber: Long): SpaceEntity
}