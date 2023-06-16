package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.BookingInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.Bookings

interface BookingRepository {

    suspend fun getBookingsByUserId(query: Long): Bookings

    suspend fun getBookingsByCarWashId(query: Long): Bookings

    suspend fun createBooking(userId: Long,
                              carWashId: Long,
                              timeBooking: String,
                              carModel: String,
                              qrCode: String)

}