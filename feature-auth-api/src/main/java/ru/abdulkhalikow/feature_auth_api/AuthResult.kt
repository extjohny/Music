package ru.abdulkhalikow.feature_auth_api

sealed class AuthResult {
    data class Success(val accessToken: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
    object Cancelled : AuthResult()
}