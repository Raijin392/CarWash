package ru.kpfu.itis.galiakhmetovniyaz.carwash.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.AuthRepositoryImpl
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.auth.datasource.AuthApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.BookingRepositoryImpl
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.booking.datasource.BookingApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.CarWashRepositoryImpl
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.datasource.CarWashApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.RegistrationRepositoryImpl
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.registration.datasource.RegistrationApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.auth.AuthUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking.BookingRepository
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking.CreateBookingUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking.GetBookingsByCarWashIdUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking.GetBookingsByUserIdUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.carWash.GetCarWashesUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration.RegistrationUseCase

object DataContainer {

    private const val BASE_URL = "http://locakhost:8080/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val authApi = retrofit.create(AuthApi::class.java)
    val authRepository = AuthRepositoryImpl(authApi)
    val authUseCase: AuthUseCase
        get() = AuthUseCase(authRepository)

    private val registrationApi = retrofit.create(RegistrationApi::class.java)
    private val registrationRepository = RegistrationRepositoryImpl(registrationApi)
    val registrationUseCase: RegistrationUseCase
        get() = RegistrationUseCase(registrationRepository)

    private val getCarWashesApi = retrofit.create(CarWashApi::class.java)
    private val carWashesRepository = CarWashRepositoryImpl(getCarWashesApi)
    val getCarWashesUseCase: GetCarWashesUseCase
        get() = GetCarWashesUseCase(carWashesRepository)

    private val bookingApi = retrofit.create(BookingApi::class.java)
    private val bookingRepository = BookingRepositoryImpl(bookingApi)
    val createBookingUseCase: CreateBookingUseCase
        get() = CreateBookingUseCase(bookingRepository)
    val getBookingsByUserIdUseCase: GetBookingsByUserIdUseCase
        get() = GetBookingsByUserIdUseCase(bookingRepository)
    val getBookingsByCarWashIdUseCase: GetBookingsByCarWashIdUseCase
        get() = GetBookingsByCarWashIdUseCase(bookingRepository)


}