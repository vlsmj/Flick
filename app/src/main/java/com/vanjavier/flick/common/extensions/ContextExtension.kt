package com.vanjavier.flick.common.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

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