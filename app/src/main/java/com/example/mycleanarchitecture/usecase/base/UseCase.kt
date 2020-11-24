package com.example.mycleanarchitecture.usecase.base

import com.example.mycleanarchitecture.presentation.util.exception.Failure
import com.example.mycleanarchitecture.presentation.util.functional.Either
import kotlinx.coroutines.*


/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This abstraction represents an execution unit for different use cases (this means than any use
 * case in the application should implement this contract).
 *
 * By convention each [UseCase] implementation will execute its job in a background thread
 * (kotlin coroutine) and will post the result in the UI thread.
 */
abstract class UseCase<out Type, in Params> where Type : Any {

    private val job = Job()

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = CoroutineScope(job + Dispatchers.IO).async { run(params) }
        CoroutineScope(Dispatchers.Main).launch { onResult(job.await()) }
    }

    fun cancel() {
        job.cancel()
    }

    class None
}
