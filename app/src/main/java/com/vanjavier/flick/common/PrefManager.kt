package com.vanjavier.flick.common

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.vanjavier.flick.common.util.TimeUtil.getCurrentDateAndTime

class PrefManager(
    private val application: Application,
) {

    companion object {
        private const val PREF_SHARED = "PREF_SHARED"
        private const val PREF_LAST_TIME_CHECKED = "PREF_LAST_TIME_CHECKED"
    }

    private fun getSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(PREF_SHARED, Context.MODE_PRIVATE)
    }

    /**
     * Get and Set the last time visited.
     */
    var lastTimeChecked: String?
        get() = getSharedPreferences().getString(
            PREF_LAST_TIME_CHECKED, getCurrentDateAndTime()
        )
        set(value) {
            getSharedPreferences().edit {
                putString(PREF_LAST_TIME_CHECKED, value)
            }
        }
}