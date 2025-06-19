package com.paul.splitmoney.utils

import android.content.Context
import android.content.SharedPreferences

class DataPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SplitMoneyPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USER_ID = "user_id"
    }

    fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    fun clearUserId() {
        sharedPreferences.edit().remove(KEY_USER_ID).apply()
    }
}
