package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation

import android.app.Application
import com.yandex.mapkit.MapKitFactory
import ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.fragment.MainMapFragment

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(MainMapFragment.mapKitApiKey)
        MapKitFactory.initialize(this)
    }
}