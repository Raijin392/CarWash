package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking

import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.BookingInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.Bookings

class GetBookingsByUserIdUseCase (
    private val bookingRepository: BookingRepository
) {

    suspend operator fun invoke(
        query: Long
    ) : Bookings = bookingRepository.getBookingsByUserId(query)

}