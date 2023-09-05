package com.example.travelguide.viewModel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class RegistrationViewModel(application: Application) : AndroidViewModel(application) {
    val sharedPreferences : SharedPreferences = application.getSharedPreferences("pref_pofile", Context.MODE_PRIVATE)

    fun registerUser(name: String, surname: String) {
        sharedPreferences.edit().apply {
            putString("name", name)
            putString("surname", surname)
            apply()
        }
    }
}