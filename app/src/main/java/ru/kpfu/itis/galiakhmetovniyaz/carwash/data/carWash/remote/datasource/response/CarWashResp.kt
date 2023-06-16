package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.datasource.response

import com.google.gson.annotations.SerializedName

data class CarWashResp (
    @SerializedName("body")
    val carWashes: List<CarWash>
)

data class CarWash (
    @SerializedName("id")
    val id: Long,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double
)

