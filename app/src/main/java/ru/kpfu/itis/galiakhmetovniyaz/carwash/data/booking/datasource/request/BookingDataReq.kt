package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.request

import com.google.gson.annotations.SerializedName

data class BookingDataReq(
    @SerializedName("userId")
    val userId: Long,
    @SerializedName("carWashId")
    val carWashId: Long,
    @SerializedName("timeBooking")
    val timeBooking: String,
    @SerializedName("carModel")
    val carModel: String,
    @SerializedName("qrCode")
    val qrCode: String
)