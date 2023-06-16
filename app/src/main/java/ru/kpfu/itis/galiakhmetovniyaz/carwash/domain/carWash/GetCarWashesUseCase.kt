package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.carWash

import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.CarWashes
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration.RegistrationRepository

class GetCarWashesUseCase (
    private val carWashesRepository: CarWashesRepository
) {
    suspend operator fun invoke() : CarWashes = carWashesRepository.getCarWashes()
}