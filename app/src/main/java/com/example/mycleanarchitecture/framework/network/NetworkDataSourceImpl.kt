package com.example.mycleanarchitecture.framework.network

import com.example.mycleanarchitecture.data.source.NetworkDataSource
import com.example.mycleanarchitecture.presentation.util.exception.Failure
import com.example.mycleanarchitecture.presentation.util.functional.Either
import com.example.mycleanarchitecture.framework.network.api.SpaceApi
import com.example.mycleanarchitecture.presentation.util.mapper.toSpace
import retrofit2.Call

class NetworkDataSourceImpl(private val spaceApi: SpaceApi) :
    NetworkDataSource {

    override fun getSpaces() =
        request(
            spaceApi.getAllPastLaunches(),
            { spaceResponseList ->
                spaceResponseList.map { spaceResponse ->
                    spaceResponse.toSpace()
                }
            }, emptyList()
        )

    private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Either.Right(transform((response.body() ?: default)))
                false -> Either.Left(Failure.ServerError)
            }
        } catch (exception: Throwable) {
            Either.Left(Failure.ServerError)
        }
    }
}