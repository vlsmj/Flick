package com.vanjavier.flick.common.util

/**
 * Util used as a wrapper in using Flows.
 *
 * @param data the data being passed.
 * @param errorMessage the error message being passed.
 */
sealed class Resource<T>(val data: T? = null, val errorMessage: UiText? = null) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(errorMessage: UiText?, data: T? = null) : Resource<T>(data, errorMessage)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}