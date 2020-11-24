package com.example.mycleanarchitecture.presentation.util.exception

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
    object ListNotAvailableError : Failure()
}
