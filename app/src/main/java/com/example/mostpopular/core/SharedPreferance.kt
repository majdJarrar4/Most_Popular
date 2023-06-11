package com.example.mostpopular.core

import android.content.SharedPreferences
import com.example.mostpopular.authentication.ui.RegisterFragment
import javax.inject.Inject

class SharedPreferance @Inject constructor(private var preferences: SharedPreferences) {

    fun getEmail(): String {
        var name = preferences.getString(RegisterFragment.USER_EMAIL, "")
        return name.toString()
    }

    fun saveEmailUser(email: String) {
        val editor = preferences.edit()
        editor.putString(RegisterFragment.USER_EMAIL, email)
        editor.apply()
    }
}