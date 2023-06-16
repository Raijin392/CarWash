package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookingViewModelFactory(private val param: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookingViewModel::class.java)) {
            return BookingViewModel(param) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}