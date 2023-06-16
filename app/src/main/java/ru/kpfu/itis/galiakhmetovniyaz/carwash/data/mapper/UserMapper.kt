package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.mapper

import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.response.UserDataAuthResp
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.response.UserDataRegistrResp
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

fun UserDataRegistrResp.toUserInfo(): UserInfo = UserInfo(
    id = id,
    username = null
)

fun UserDataAuthResp.toUserInfo(): UserInfo = UserInfo(
    id = id,
    username = null
)