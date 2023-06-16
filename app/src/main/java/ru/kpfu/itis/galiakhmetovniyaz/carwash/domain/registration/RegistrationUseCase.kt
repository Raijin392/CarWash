package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration

import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

class RegistrationUseCase(
    private val registrationRepository: RegistrationRepository
) {

    suspend operator fun invoke(
        username: String,
        password: String
    ) : UserInfo = registrationRepository.signIn(username, password)

}