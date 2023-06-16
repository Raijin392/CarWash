package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import kotlinx.coroutines.launch
import ru.kpfu.itis.galiakhmetovniyaz.carwash.R
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.DataContainer
import ru.kpfu.itis.galiakhmetovniyaz.carwash.databinding.ActivityMainBinding
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.auth.AuthRepository
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration.RegistrationRepository
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration.RegistrationUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController

    }

}