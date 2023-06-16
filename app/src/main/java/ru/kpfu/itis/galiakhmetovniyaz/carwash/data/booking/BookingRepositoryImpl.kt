package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking

import retrofit2.http.GET
import retrofit2.http.Query
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.BookingApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.request.BookingDataReq
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.response.BookingDataResp
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.mapper.toBookings
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking.BookingRepository
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.BookingInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.Bookings

class BookingRepositoryImpl(
    private val api: BookingApi
) : BookingRepository {

    override suspend fun getBookingsByUserId(
        query: Long
    ): Bookings = Bookings(api.getBookingsByUserId(query).bookings.toBookings())

    override suspend fun getBookingsByCarWashId(
        query: Long
    ): Bookings = Bookings(api.getBookingsByCarWashId(query).bookings.toBookings())

    override suspend fun createBooking(
        userId: Long,
        carWashId: Long,
        timeBooking: String,
        carModel: String,
        qrCode: String
    ) {
        api.createBooking(
            BookingDataReq(
                userId = userId,
                carWashId = carWashId,
                timeBooking = timeBooking,
                carModel = carModel,
                qrCode = qrCode
            )
        )
    }

}