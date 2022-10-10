package com.vanjavier.flick.common.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    /**
     * Get the current date and time and with custom format.
     */
    fun getCurrentDateAndTime(): String =
        SimpleDateFormat("MMM d, yyyy h:mm a", Locale.getDefault()).format(
            Date()
        )
}