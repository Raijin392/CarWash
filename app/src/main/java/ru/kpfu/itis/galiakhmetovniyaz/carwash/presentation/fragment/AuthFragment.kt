package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.fragment

import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.kpfu.itis.galiakhmetovniyaz.carwash.R
import ru.kpfu.itis.galiakhmetovniyaz.carwash.databinding.FragmentAuthBinding
import ru.kpfu.itis.galiakhmetovniyaz.carwash.databinding.FragmentRegistrationBinding
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.DataContainer
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.UserPreferences
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.auth.AuthUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.auth.ValidateAuthUserPasswordUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.registration.RegistrationUseCase

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private var binding: FragmentAuthBinding? = null

    private val authUseCase: AuthUseCase = DataContainer.authUseCase
    private val validateAuthUserPasswordUseCase: ValidateAuthUserPasswordUseCase = ValidateAuthUserPasswordUseCase()

    private lateinit var userPreferences: UserPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreferences = UserPreferences(view.context)
        binding = FragmentAuthBinding.bind(view)

        binding?.run {

            tvRegistration.paintFlags = Paint.UNDERLINE_TEXT_FLAG

            btnLogin.setOnClickListener {
                login(
                    etUsername.text.toString(),
                    etPassword.text.toString()
                )
            }

            tvRegistration.setOnClickListener {
                findNavController().navigate(R.id.registrationFragment)
            }

        }

    }

    private fun login(username: String, password: String) {
        lifecycleScope.launch {
            if (validateAuthUserPasswordUseCase(password)) {
                try {
                    authUseCase(username, password).also { userInfo ->
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