package com.example.mycleanarchitecture.data.source

import com.example.mycleanarchitecture.presentation.util.exception.Failure
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.presentation.util.functional.Either

interface DatabaseDataSource {

    fun saveAll(spaces: List<Space>) : Either<Failure, Unit>

    fun getAll() : Either<Failure, List<Space>>

    fun getSpaceByFlightNumber(flightNumber: Long): Either<Failure, Space>

}