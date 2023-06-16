package ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote

import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.carWash.remote.datasource.CarWashApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.data.mapper.toCarWashes
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.carWash.CarWashesRepository
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities.CarWashes

class CarWashRepositoryImpl (
    private val api: CarWashApi
) : CarWashesRepository {

    override suspend fun getCarWashes() : CarWashes = CarWashes(api.getCarWashes().carWashes.toCarWashes())

}