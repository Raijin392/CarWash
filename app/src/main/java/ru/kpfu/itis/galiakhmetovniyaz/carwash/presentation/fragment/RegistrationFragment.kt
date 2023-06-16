package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.fragment

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.kpfu.itis.galiakhmetovniyaz.carwash.R
import ru.kpfu.itis.galiakhmetovniyaz.carwash.databinding.FragmentRegistrationBinding
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.DataContainer
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.UserPreferences
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration.RegistrationUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration.ValidateRegistrationUserPasswordUseCase

class RegistrationFragment : Fragment(R.layout.fragment_registration) {

    private var binding: FragmentRegistrationBinding? = null

    private val registrationUseCase: RegistrationUseCase = DataContainer.registrationUseCase
    private val validateRegistrationUserPasswordUseCase:
            ValidateRegistrationUserPasswordUseCase = ValidateRegistrationUserPasswordUseCase()

    private lateinit var userPreferences: UserPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreferences = UserPreferences(view.context)
        binding = FragmentRegistrationBinding.bind(view)

        binding?.run {

            tvLogin.paintFlags = Paint.UNDERLINE_TEXT_FLAG

            btnRegistration.setOnClickListener {
                registration(
                    etUsername.text.toString(),
                    etPassword.text.toString(),
                    etPasswordRepeat.text.toString()
                )
            }

            tvLogin.setOnClickListener {
                findNavController().navigate(R.id.authFragment)
            }

        }

    }

    private fun registration(username: String, password: String, passwordRepeat: String) {
        lifecycleScope.launch {
            if (validateRegistrationUserPasswordUseCase(password, passwordRepeat)) {
                try {
                    registrationUseCase(username, password).also { userInfo ->
                        userPreferences.id = userInfo.id
                        userPreferences.username = username
                    }
                    findNavController().navigate(R.id.mainMapFragment)
                } catch (error: Throwable) {
                    error.message?.let { Log.e("TAAAAAAAAAAAAAAAAAG", it) }
                }
            } else {
                Log.e("TAAAAAAAAAAAAAAAAAG", "WrongPass")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}