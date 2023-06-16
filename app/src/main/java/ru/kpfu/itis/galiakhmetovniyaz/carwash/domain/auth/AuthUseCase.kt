package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.auth

import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

class AuthUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        username: String,
        password: String
    ) : UserInfo = authRepository.login(username, password)

}