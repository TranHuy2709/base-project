package com.huytq.baseproject.data.remote.utils

enum class DataResponseStatus {
    LOADING, SUCCESS, ERROR, IDLE, EXPIRE, CACHE, LOGOUT
}

sealed class DataResponse<T> constructor(val dataResponseStatus: DataResponseStatus) {
    class DataEmptyResponse<T> : DataResponse<T>(DataResponseStatus.IDLE)
    class DataErrorResponse<T>(val throwable: Throwable) : DataResponse<T>(DataResponseStatus.ERROR)
    data class DataSuccessResponse<T>(val body: T) : DataResponse<T>(DataResponseStatus.SUCCESS)
    class DataLoadingResponse<T> : DataResponse<T>(DataResponseStatus.LOADING)
    class DataExpireResponse<T> : DataResponse<T>(DataResponseStatus.EXPIRE)
    class DataCacheResponse<T> : DataResponse<T>(DataResponseStatus.CACHE)
    class DataLogoutResponse<T> : DataResponse<T>(DataResponseStatus.LOGOUT)
}