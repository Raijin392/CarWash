package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource

import retrofit2.http.Body
import retrofit2.http.POST
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.request.UserDataRegistrReq
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.response.UserDataRegistrResp

interface RegistrationApi {

    @POST("registration")
    suspend fun registration(@Body userData: UserDataRegistrReq) : UserDataRegistrResp

}