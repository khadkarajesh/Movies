package com.smartmobe.kservice.data.rest.retrofit

import com.crushcoder.kmovies.rest.retrofit.ErrorBody
import com.google.gson.annotations.SerializedName

data class ApiError(@SerializedName("status") var status: ErrorBody)