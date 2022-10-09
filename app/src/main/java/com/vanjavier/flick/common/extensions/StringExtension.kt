package com.vanjavier.flick.common.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * Format String to date and time and with custom format.
 */
fun String.formatDateTime(): String {
    return SimpleDateFormat("MMM d, yyyy h:mm a", Locale.getDefault()).format(
        Date()
    )
}

/**
 * Get the year only.
 */
fun String.getDateYear(): String {
    return SimpleDateFormat("yyyy", Locale.getDefault()).format(
        Date()
    )
}