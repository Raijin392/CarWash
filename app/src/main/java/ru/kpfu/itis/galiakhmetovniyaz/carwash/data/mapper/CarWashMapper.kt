package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.mapper

import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.response.UserDataAuthResp
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.datasource.response.CarWash
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.datasource.response.CarWashResp
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.response.UserDataRegistrResp
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.CarWashInfo
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

fun CarWash.toCarWashInfo(): CarWashInfo = CarWashInfo(
    id = id,
    latitude = latitude,
    longitude = longitude
)

fun List<CarWash>.toCarWashes(): List<CarWashInfo> = map {
    it.toCarWashInfo()
}