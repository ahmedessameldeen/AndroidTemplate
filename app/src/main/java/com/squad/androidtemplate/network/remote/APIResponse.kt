package com.squad.androidtemplate.network.remote

sealed class APIResponse<T> {
    data class Success<T>(val body: T): APIResponse<T>()
    data class Error<T>(val errorMessage: String?, val errorCode: Int): APIResponse<T>()
    data class NetWorkError<T>(val errorMessage: String?): APIResponse<T>()
    data class ConnectionTimeOut<T>(val errorMessage: String?): APIResponse<T>()
}