package com.example.mobile

import android.content.Context
import android.content.SharedPreferences

class OlaUserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveUserName(username: String, str: String) {
        sharedPreferences.edit().putString(username, str).apply()
    }

    fun getUserName(username: String): String {
        return sharedPreferences.getString(username, "") ?: ""
    }
}
