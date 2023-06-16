package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth

import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.AuthApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.request.UserDataAuthReq
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.mapper.toUserInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.auth.AuthRepository
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

class AuthRepositoryImpl(
    private val authApi: AuthApi
) : AuthRepository {

    override suspend fun login(username: String, password: String): UserInfo {
        return authApi.login(
            UserDataAuthReq(
                username = username,
                password = password,
            )
        ).toUserInfo()
    }

    override suspend fun logout() {
        TODO("Not yet implemented")
    }

}