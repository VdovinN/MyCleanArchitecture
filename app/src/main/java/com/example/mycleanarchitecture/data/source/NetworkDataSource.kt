package com.example.mycleanarchitecture.data.source

import com.example.mycleanarchitecture.presentation.util.exception.Failure
import com.example.mycleanarchitecture.domain.Space
import com.example.mycleanarchitecture.presentation.util.functional.Either

interface NetworkDataSource {

    fun getSpaces() : Either<Failure, List<Space>>

}