package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.response

import com.google.gson.annotations.SerializedName

data class UserDataRegistrResp(
    @SerializedName("body")
    val id: Long,
    @SerializedName("login")
    val username: String?,
    @SerializedName("password")
    val password: String?
)
