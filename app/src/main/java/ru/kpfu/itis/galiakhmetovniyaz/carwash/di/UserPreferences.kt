package ru.kpfu.itis.galiakhmetovniyaz.carwash.di

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("userPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_ID = "id"
        private const val KEY_USERNAME = "username"
    }

    var id: Long
        get() = sharedPreferences.getLong(KEY_ID, 0L)
        set(value) = sharedPreferences.edit().putLong(KEY_ID, value).apply()

    var username: String?
        get() = sharedPreferences.getString(KEY_USERNAME, null)
        set(value) = sharedPreferences.edit().putString(KEY_USERNAME, value).apply()

}