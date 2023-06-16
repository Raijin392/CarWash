package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.galiakhmetovniyaz.carwash.R
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.UserPreferences

class StartFragment : Fragment(R.layout.fragment_start) {

    private lateinit var userPreferences: UserPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreferences = UserPreferences(view.context)

        if (userPreferences.username == null) {
            findNavController().navigate(R.id.authFragment)
        } else {
            findNavController().navigate(R.id.mainMapFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }

}