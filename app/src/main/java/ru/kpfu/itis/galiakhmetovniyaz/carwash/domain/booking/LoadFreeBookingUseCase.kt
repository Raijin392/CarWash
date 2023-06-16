package ru.kpfu.itis.galiakhmetovniyaz.carwash.domain.booking

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import ru.kpfu.itis.galiakhmetovniyaz.carwash.di.DataContainer
import java.time.LocalTime

class LoadFreeBookingUseCase {

    val getBookingsByCarWashIdUseCase = DataContainer.getBookingsByCarWashIdUseCase
    val freeBookingsTime = listOf("0:00", "1:00", "2:00", "3:00", "4:00", "5:00", "6:00", "7:00",
                                  "8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00",
                                  "15:00", "16:00", "17:00", "18:00", "19:00", "20:00",
                                  "21:00", "22:00", "23:00")

    @RequiresApi(Build.VERSION_CODES.O)
    suspend operator fun invoke(carWashId: Long) : ArrayList<String> {
        val result = ArrayList<String>()
        val bookings = getBookingsByCarWashIdUseCase(carWashId).bookings
        val takenTime = ArrayList<String>()
        if (bookings.isNotEmpty()) {
            for (book in bookings) {
                takenTime.add(book.washTime.split(" ")[1].split(":")[0])
            }
        } else {
            takenTime.add("")
        }


        val currentTime = LocalTime.now()
        val hours = currentTime.hour

        for (fbt in freeBookingsTime) {
            val hrs = fbt.split(":")[0].toInt()
            if ((fbt.split(":")[0] !in takenTime) && (hours < hrs)) {
                result.add(fbt)
            }
        }
        Log.e("LLLLLLIIIIISSSSTTTT", result.toString())
        return result
    }

}