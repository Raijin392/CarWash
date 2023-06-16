package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.request

import com.google.gson.annotations.SerializedName

data class UserDataRegistrReq (
    @SerializedName("login")
    val username: String,
    @SerializedName("password")
    val password: String
)

