package com.missingcontroller.parttimer

import android.content.Context
import android.content.SharedPreferences
import com.missingcontroller.parttimer.PartsTimerApplication.Companion.context

object CredentialManager {
    private val tag: String = "CREDENTIALS MANAGER"

    const val SHARED_PREFERENCES_KEY = "parts_timer"
    const val SAVE_TOKEN = "save_token"
    const val SAVE_USER_ID = "save_user_id"

    private fun getSharedPreferences(): SharedPreferences? {
        return context?.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
    }

    fun erasePrefs(){
        getSharedPreferences()?.edit()?.clear()?.apply()
    }

    fun saveToken(token: String) {
        getSharedPreferences()?.edit()?.putString(SAVE_TOKEN, token)?.apply()
    }

    fun getToken(): String {
        return getSharedPreferences()?.getString(SAVE_TOKEN, "") ?: ""
    }

    fun saveUserId(userId: String) {
        getSharedPreferences()?.edit()?.putString(SAVE_USER_ID, userId)?.apply()
    }

    fun getUserId(): String {
        return getSharedPreferences()?.getString(SAVE_USER_ID, "") ?: ""
    }
}