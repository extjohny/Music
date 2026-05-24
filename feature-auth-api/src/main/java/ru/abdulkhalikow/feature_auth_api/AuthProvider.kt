package ru.abdulkhalikow.feature_auth_api

interface AuthProvider {
    suspend fun getAccessToken(): String?
    suspend fun isAuthorized(): Boolean
    suspend fun logout()
}