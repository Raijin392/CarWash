package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration

import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

interface RegistrationRepository {

    suspend fun signIn(username: String, password: String) : UserInfo

}