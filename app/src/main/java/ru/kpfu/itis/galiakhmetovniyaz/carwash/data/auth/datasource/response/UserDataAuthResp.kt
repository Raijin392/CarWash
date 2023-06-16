package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.response

import com.google.gson.annotations.SerializedName

data class UserDataAuthResp(
    @SerializedName("body")
    val id: Long,
    @SerializedName("login")
    val username: String?,
    @SerializedName("password")
    val password: String?
)
