package com.smartmobe.kservice.data.rest.response

import com.google.gson.annotations.SerializedName

data class User(val id: Long,
                @SerializedName("first_name") var firstName: String,
                @SerializedName("last_name") var lastName: String,
                @SerializedName("middle_name") var middleName: String,
                @SerializedName("email") val email: String,
                @SerializedName("image_url") val imageUrl: String,
                @SerializedName("first_time") val firstTime: Boolean)