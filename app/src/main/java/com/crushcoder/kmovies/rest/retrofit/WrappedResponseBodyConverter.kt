package com.smartmobe.kservice.data.rest.retrofit

import okhttp3.ResponseBody
import retrofit2.Converter
import java.io.IOException

internal class WrappedResponseBodyConverter<T>(private val converter: Converter<ResponseBody, BaseResponse<T>>) : Converter<ResponseBody, T> {
    @Throws(IOException::class)
    override fun convert(value: ResponseBody): T? {
        val response = converter.convert(value)
        return response.body
    }
}
