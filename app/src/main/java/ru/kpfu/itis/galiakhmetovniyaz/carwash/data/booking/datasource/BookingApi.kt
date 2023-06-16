package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.request.BookingDataReq
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.response.BookingDataResp

interface BookingApi {

    @GET("booking/carWashId")
    suspend fun getBookingsByCarWashId(
        @Query("id") carWashId: Long
    ) : BookingDataResp

    @GET("booking/userId")
    suspend fun getBookingsByUserId(
        @Query("id") userId: Long
    ) : BookingDataResp

    @POST("booking/create")
    suspend fun createBooking(@Body bookingDataReq: BookingDataReq)

}