package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration

class ValidateRegistrationUserPasswordUseCase {

    private val PASSWORD_LENGTH: Int = 6

    operator fun invoke(
        password: String,
        passwordRepeat: String,
    ): Boolean = password.checkLength() && password.myEquals(passwordRepeat)

    private fun String.checkLength() = this.length >= PASSWORD_LENGTH

    private fun String.myEquals(passwordRepeat: String) = this == passwordRepeat

}