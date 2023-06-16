package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking

import retrofit2.http.Query
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.BookingInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.Bookings
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

class GetBookingsByCarWashIdUseCase(
    private val bookingRepository: BookingRepository
) {

    suspend operator fun invoke(
        query: Long
    ) : Bookings = bookingRepository.getBookingsByCarWashId(query)

}