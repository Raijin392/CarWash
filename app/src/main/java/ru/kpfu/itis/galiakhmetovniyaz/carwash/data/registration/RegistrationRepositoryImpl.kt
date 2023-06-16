package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration

import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.mapper.toUserInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.request.UserDataRegistrReq
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.RegistrationApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.response.UserDataRegistrResp
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration.RegistrationRepository

class RegistrationRepositoryImpl(
    private val registrationApi: RegistrationApi
) : RegistrationRepository {

    override suspend fun signIn(username: String, password: String): UserInfo {
        return registrationApi.registration(
            UserDataRegistrReq(
                username = username,
                password = password,
            )
        ).toUserInfo()
    }

}