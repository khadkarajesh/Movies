package com.smartmobe.kservice.data.rest.retrofit

import com.google.gson.Gson
import retrofit2.HttpException

object ErrorUtils {
    @JvmStatic
    fun getApiError(throwable: Throwable): BaseResponse<*> {
        var exception: HttpException = throwable as HttpException
        var errorBody = exception.response().errorBody()
        return Gson().fromJson(errorBody?.string(), BaseResponse::class.java)
    }

    @JvmStatic
    fun getCode(throwable: Throwable): Int {
        var exception: HttpException = throwable as HttpException
        return exception.code()
    }
}