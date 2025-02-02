package com.example.moonsoon.data.resource


// represent different states of a resource. This is useful for handling various states in a clean and type-safe manner,
// especially in asynchronous operations like network requests
// This similar to the Bloc pattern [app state] in Flutter
sealed class ResourceState<T> {
    class Loading<T> : ResourceState<T>()
    data class Success<T>(val data: T): ResourceState<T>()
    data class Error<T>(val error: Any) : ResourceState<T>()
}