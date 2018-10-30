package com.smartmobe.kservice.data.rest.retrofit

import com.google.gson.annotations.SerializedName


class BaseResponse<R> {
    @SerializedName("results")
    val body: R? = null

    val page: Int = 0
    val total_results: Int = 0
    val total_pages: Int = 0
}
