package com.example.mycleanarchitecture.framework.database

import com.example.mycleanarchitecture.data.source.DatabaseDataSource
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.presentation.util.functional.Either
import com.example.mycleanarchitecture.presentation.util.functional.map
import com.example.mycleanarchitecture.framework.database.dao.SpaceDao
import com.example.mycleanarchitecture.presentation.util.mapper.toSpace
import com.example.mycleanarchitecture.presentation.util.mapper.toSpaceEntity

class DatabaseDataSourceImpl(
    private val spaceDao: SpaceDao
) : DatabaseDataSource {

    override fun saveAll(spaces: List<Space>) =
        Either.Right(spaceDao.insert(spaces.map { space -> space.toSpaceEntity() }))

    override fun getAll() =
        Either.Right(spaceDao.selectAllLaunches().map { spaceEntity -> spaceEntity.toSpace() })

    override fun getSpaceByFlightNumber(flightNumber: Long) =
        Either.Right(spaceDao.selectLaunchByFlightNumber(flightNumber)).map { it.toSpace() }


}