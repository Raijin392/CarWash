package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.auth

class ValidateAuthUserPasswordUseCase {

    private val PASSWORD_LENGTH: Int = 6

    operator fun invoke(
        password: String
    ): Boolean = password.checkLength()

    private fun String.checkLength() = this.length >= PASSWORD_LENGTH

}