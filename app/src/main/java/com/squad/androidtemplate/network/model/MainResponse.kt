package com.code95.magrabi.mvvmData.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainResponse<T>(
    @Expose
    @SerializedName("success")
    var success: Boolean,

    @Expose
    @SerializedName("message")
    var message: String,

    @Expose
    @SerializedName("code")
    var code: Int,

    @Expose
    @SerializedName("data")
    var data: T?
)
