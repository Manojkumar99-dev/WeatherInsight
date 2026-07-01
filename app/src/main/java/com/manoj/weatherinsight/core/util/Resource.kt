package com.manoj.weatherinsight.core.util

/**
 * A generic sealed class that represents a network or database resource.
 * It encapsulates the state of the data being fetched (Success, Error, or Loading).
 *
 * @param T The type of data being handled.
 * @author Manojkumar
 */
sealed class Resource<out T> {

    /**
     * Represents a successful operation with the resulting data.
     */
    data class Success<T>(val data: T) : Resource<T>()

    /**
     * Represents a failed operation with an error message.
     */
    data class Error(val message: String) : Resource<Nothing>()

    /**
     * Represents an ongoing operation.
     */
    data object Loading : Resource<Nothing>()
}
