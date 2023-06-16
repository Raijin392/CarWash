package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource

import retrofit2.http.Body
import retrofit2.http.POST
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.request.UserDataAuthReq
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.response.UserDataAuthResp
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.UserInfo

interface AuthApi {

    @POST("authorization")
    suspend fun login(@Body userData: UserDataAuthReq) : UserDataAuthResp

}