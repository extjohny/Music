package ru.abdulkhalikow.feature_auth_impl.data

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface JamendoAuthApi {

    @FormUrlEncoded
    @POST("v3.0/oauth/grant")
    suspend fun getToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String,
        @Field("grant_type") grantType: String = "authorization_code"
    ): JamendoTokenResponse

    @FormUrlEncoded
    @POST("v3.0/oauth/grant")
    suspend fun refreshToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("refresh_token") refreshToken: String,
        @Field("grant_type") grantType: String = "refresh_token"
    ): JamendoTokenResponse
}