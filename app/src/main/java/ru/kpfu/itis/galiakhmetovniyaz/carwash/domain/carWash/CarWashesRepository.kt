package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.carWash

import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.CarWashes

interface CarWashesRepository {

    suspend fun getCarWashes(): CarWashes

}