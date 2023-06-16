package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.datasource

import retrofit2.http.GET
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.datasource.response.CarWashResp

interface CarWashApi {

    @GET("carWash")
    suspend fun getCarWashes(): CarWashResp

}