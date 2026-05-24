package ru.abdulkhalikow.feature_auth_impl.data

import ru.abdulkhalikow.feature_auth_api.AuthProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthProviderImpl @Inject constructor(
    private val localDataSource: AuthLocalDataSource
) : AuthProvider {

    override suspend fun getAccessToken(): String? {
        return localDataSource.getAuthToken()?.accessToken
    }

    override suspend fun isAuthorized(): Boolean {
        val token = localDataSource.getAuthToken() ?: return false
        return System.currentTimeMillis() < token.expiresAt
    }

    override suspend fun logout() {
        localDataSource.clear()
    }
}