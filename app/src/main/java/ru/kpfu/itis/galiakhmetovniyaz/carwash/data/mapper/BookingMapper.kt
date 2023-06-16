package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.mapper

import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.response.Booking
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.BookingInfo

fun Booking.toBookingInfo(): BookingInfo = BookingInfo(
    carModel = carModel,
    carWashId = carWash.id,
    qrCode = qrCode,
    washTime = washTime
)

fun List<Booking>.toBookings(): List<BookingInfo> = map {
    it.toBookingInfo()
}
