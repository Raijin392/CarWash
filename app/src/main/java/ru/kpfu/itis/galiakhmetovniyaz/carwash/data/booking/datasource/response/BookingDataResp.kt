package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.response


import com.google.gson.annotations.SerializedName

data class BookingDataResp(
    @SerializedName("body")
    val bookings: List<Booking>
)

data class Booking(
    @SerializedName("carModel")
    val carModel: String,
    @SerializedName("carWash")
    val carWash: CarWash,
    @SerializedName("qrCode")
    val qrCode: String,
    @SerializedName("washTime")
    val washTime: String
)

data class CarWash(
    @SerializedName("id")
    val id: Long
)