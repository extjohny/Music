package ru.abdulkhalikow.feature_auth_impl.data

data class AuthToken(
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Long
)