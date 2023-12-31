package com.example.nilay.common

// A sealed class to represent the different states of a network operation
sealed class Resource<out T : Any> {
    object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val errorMessage: String) : Resource<Nothing>()
}