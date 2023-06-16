package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking.LoadFreeBookingUseCase

class BookingViewModel(
    private val param: Long
) : ViewModel() {

    private val _data = MutableLiveData<ArrayList<String>>()
    val data: LiveData<ArrayList<String>>
        get() = _data

    private val loadFreeBookingUseCase: LoadFreeBookingUseCase = LoadFreeBookingUseCase()

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadData() {

        viewModelScope.launch {
            val loadedData = loadFreeBookingUseCase(param)
            _data.value = loadedData
        }

    }

}
