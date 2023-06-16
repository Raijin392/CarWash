package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities

import com.google.gson.annotations.SerializedName

data class BookingInfo(
    val carModel: String,
    val carWashId: Long,
    val qrCode: String,
    val washTime: String,
)

data class Bookings(
    val bookings: List<BookingInfo>
)