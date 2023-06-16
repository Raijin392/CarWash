package ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.kpfu.itis.galiakhmetovniyaz.carwash.R
import ru.kpfu.itis.galiakhmetovniyaz.carwash.databinding.FragmentBookingBinding
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.DataContainer
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.UserPreferences
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking.CreateBookingUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking.LoadFreeBookingUseCase
import ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.dialogFragment.QrCodeDialogFragment
import ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.viewModel.BookingViewModel
import ru.kpfu.itis.galiakhmetovniyaz.carwash.presentation.viewModel.BookingViewModelFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BookingFragment : Fragment(R.layout.fragment_booking) {

    private var binding: FragmentBookingBinding? = null

    private lateinit var userPreferences: UserPreferences

    private lateinit var bookingViewModel: BookingViewModel

    private val createBookingUseCase: CreateBookingUseCase = DataContainer.createBookingUseCase

    private var timeBookings = ArrayList<String>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userPreferences = UserPreferences(view.context)
        binding = FragmentBookingBinding.bind(view)

        binding?.run {
            val carWashId = arguments?.getLong("carWashId") ?: 0L
            bookingViewModel = ViewModelProvider(this@BookingFragment, BookingViewModelFactory(carWashId)).get(BookingViewModel::class.java)

            tvCarwashId.text = "ID CarWash: ${carWashId.toString()}"

            val adapter: ArrayAdapter<String> = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                timeBookings
            )
            bookingSpiner.adapter = adapter

            bookingViewModel.loadData()

            bookingViewModel.data.observe(viewLifecycleOwner) { loadedData ->
                timeBookings.clear()
                timeBookings.addAll(loadedData)
                adapter.notifyDataSetChanged()
            }

            bookingSpiner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    // Обработка выбора элемента в Spinner
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Обработка, если не выбран ни один элемент в Spinner
                }
            }

            btnBook.setOnClickListener {
                val selectedTime = bookingSpiner.selectedItem as? String
                if (selectedTime != null) {
                    val currentDateTime = LocalDateTime.now()
                    val hour = selectedTime.split(":")[0].toInt()
                    val modifiedDateTime = currentDateTime.withHour(hour)
                    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS")
                    val formattedDateTime = modifiedDateTime.format(formatter)

                    lifecycleScope.launch {
                        createBookingUseCase(userId = userPreferences.id,
                            carWashId = carWashId,
                            timeBooking = formattedDateTime.toString(),
                            carModel = etCarModel.text.toString(),
                            qrCode = (userPreferences.id.toString() +
                                    carWashId.toString() +
                                    selectedTime +
                                    etCarModel.text.toString()))
                    }

                    showDialog(
                        userPreferences.id.toString(),
                        carWashId.toString(),
                        selectedTime,
                        etCarModel.text.toString()
                    )
                } else {
                    // Обработка, если не выбрано время
                }
            }
        }
    }

    private fun showDialog(
        userId: String,
        carWashId: String,
        timeBooking: String,
        carModel: String
    ) {val dialogFragment = QrCodeDialogFragment.newInstance(
            userId,
            carWashId,
            timeBooking,
            carModel
        )
        dialogFragment.show(parentFragmentManager, "QrCodeDialog")
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}