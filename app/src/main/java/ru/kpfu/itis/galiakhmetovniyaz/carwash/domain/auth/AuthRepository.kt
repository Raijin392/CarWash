package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.auth

import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

interface AuthRepository {

    suspend fun login(username: String, password: String) : UserInfo

    suspend fun logout()

}