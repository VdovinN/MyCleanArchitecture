package com.example.mycleanarchitecture.usecase

import com.example.mycleanarchitecture.presentation.util.exception.Failure
import com.example.mycleanarchitecture.data.repository.SpaceRepository
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.presentation.util.functional.Either
import com.example.mycleanarchitecture.usecase.base.UseCase

class GetSpaceDetailsUseCase(
    private val spaceRepository: SpaceRepository
) : UseCase<Space, GetSpaceDetailsUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure, Space> =
        spaceRepository.getLaunchByFlightNumber(params.flightNumber)

    data class Params(val flightNumber: Long)

}