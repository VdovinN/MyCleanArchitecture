package com.example.mycleanarchitecture.usecase

import com.example.mycleanarchitecture.presentation.util.exception.Failure
import com.example.mycleanarchitecture.data.repository.SpaceRepository
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.presentation.util.functional.Either
import com.example.mycleanarchitecture.usecase.base.UseCase
import javax.inject.Inject


class GetSpacesUseCase @Inject constructor(
    private val spaceRepository: SpaceRepository
) : UseCase<List<Space>, UseCase.None>() {
    override suspend fun run(params: None): Either<Failure, List<Space>> =
        spaceRepository.getLatestLaunches()

}
