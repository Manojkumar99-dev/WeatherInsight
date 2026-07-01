package com.manoj.weatherinsight.core.util

import com.manoj.weatherinsight.R
import java.io.IOException
import retrofit2.HttpException

/**
 * Utility object to convert exceptions into user-friendly error messages.
 *
 * @author Manojkumar
 */
object ErrorUtils {

    /**
     * Maps an [Exception] to a descriptive error message string.
     * Handles network issues ([IOException]) and API-specific errors ([HttpException]).
     * 
     * @param exception The caught exception.
     * @return A user-friendly error message.
     */
    fun getMessage(exception: Exception): Int {
        return when (exception) {
            is IOException ->
                R.string.error_no_internet

            is HttpException -> {
                when (exception.code()) {
                    400 -> R.string.error_invalid_city
                    401 -> R.string.error_invalid_api_key
                    404 -> R.string.error_city_not_found
                    else -> R.string.error_server
                }
            }

            else -> R.string.error_unknown
        }
    }
}