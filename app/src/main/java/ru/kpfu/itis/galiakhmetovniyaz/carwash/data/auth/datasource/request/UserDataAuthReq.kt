package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.request

import com.google.gson.annotations.SerializedName

data class UserDataAuthReq (
    @SerializedName("login")
    val username: String,
    @SerializedName("password")
    val password: String
)