package com.example.mycleanarchitecture.data.repository

import com.example.mycleanarchitecture.data.connection.InternetConnection
import com.example.mycleanarchitecture.data.source.DatabaseDataSource
import com.example.mycleanarchitecture.data.source.NetworkDataSource
import com.example.mycleanarchitecture.presentation.util.exception.Failure
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.presentation.util.functional.Either
import com.example.mycleanarchitecture.presentation.util.functional.flatMap

class SpaceRepository(
    private val networkDataSource: NetworkDataSource,
    private val databaseDataSource: DatabaseDataSource,
    private val internetConnection: InternetConnection
) {

    fun getLaunchByFlightNumber(flightNumber: Long) =
        databaseDataSource.getSpaceByFlightNumber(flightNumber)


    fun getLatestLaunches(): Either<Failure, List<Space>> =
        if (internetConnection.isConnected()) {
            networkDataSource.getSpaces()
                .flatMap { spaceList ->
                    databaseDataSource.saveAll(spaceList)
                    getSpacesOrFailure(Failure.ListNotAvailableError)
                }
        } else {
            getSpacesOrFailure(Failure.NetworkConnectionError)
        }

    private fun getSpacesOrFailure(failure: Failure): Either<Failure, List<Space>> {
        return databaseDataSource.getAll().flatMap { spaceList ->
            if (spaceList.isNotEmpty()) {
                Either.Right(spaceList)
            } else {
                Either.Left(failure)
            }
        }
    }
}