package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking

import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.BookingInfo

class CreateBookingUseCase(
    private val bookingRepository: BookingRepository
) {

    suspend operator fun invoke(userId: Long,
                                carWashId: Long,
                                timeBooking: String,
                                carModel: String,
                                qrCode: String

    ) {
        bookingRepository.createBooking(userId,
                                        carWashId,
                                        timeBooking,
                                        carModel,
                                        qrCode)
    }

}