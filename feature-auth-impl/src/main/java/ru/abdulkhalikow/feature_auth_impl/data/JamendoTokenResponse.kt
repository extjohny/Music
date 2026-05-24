package ru.abdulkhalikow.feature_auth_impl.data

data class JamendoTokenResponse(
    val access_token: String,
    val refresh_token: String,
    val expires_in: Long,
    val token_type: String,
    val scope: String
)