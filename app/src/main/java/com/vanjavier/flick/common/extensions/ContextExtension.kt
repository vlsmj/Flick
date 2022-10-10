package com.vanjavier.flick.common.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 * Start an activity.
 *
 * @param it the activity where will be redirected to.
 * @param from the current activity.
 * @param extras optional Bundle with data.
 */
fun <T> Context.openActivity(
    it: Class<T>,
    from: Activity? = null,
    extras: Bundle.() -> Unit = {},
) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
    from?.finish()
}