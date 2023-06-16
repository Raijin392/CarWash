package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.entities

data class CarWashInfo (
    var id: Long,
    var latitude: Double,
    var longitude: Double,
)

data class CarWashes (
    var carWashes: List<CarWashInfo>
)