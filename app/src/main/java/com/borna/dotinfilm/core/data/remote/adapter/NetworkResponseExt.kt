package com.borna.dotinfilm.core.data.remote.adapter


suspend fun <R: Any, T: Any> NetworkResponse<T, DotinApiErr>.onSuccessful(callback: suspend (T) -> R): Response<R> {
    return when(this) {
        is NetworkResponse.Success -> {
            Success(callback(this.body!!))
        }
        is NetworkResponse.ApiError -> Failure(GeneralError.ApiError(this.body?.message, this.code))
        is NetworkResponse.NetworkError -> Failure(GeneralError.NetworkError)
        is NetworkResponse.UnknownError -> Failure(GeneralError.UnknownError(this.error))
    }
}

suspend fun <R: Any, T: Any> NetworkResponse<T, DotinApiErr>.onDecideWithBody(callback: suspend (T?) -> IsSucceed<R>): Response<R?> {
    return when(this) {
        is NetworkResponse.Success -> {
            if (code == 204) {
                Success(null)
            }else {
                val result = callback(this.body)
                if (result.isError) {
                    Failure(GeneralError.ApiError(result.error, code))
                } else {
                    Success(result.data)
                }
            }
        }
        is NetworkResponse.ApiError -> Failure(GeneralError.ApiError(this.body?.message, this.code))
        is NetworkResponse.NetworkError -> Failure(GeneralError.NetworkError)
        is NetworkResponse.UnknownError -> Failure(GeneralError.UnknownError(this.error))
    }
}