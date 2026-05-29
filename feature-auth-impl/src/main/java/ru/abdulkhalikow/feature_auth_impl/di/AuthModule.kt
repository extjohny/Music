package ru.abdulkhalikow.feature_auth_impl.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.abdulkhalikow.core_utils.AuthInterceptor
import ru.abdulkhalikow.core_utils.JamendoClientId
import ru.abdulkhalikow.core_utils.JamendoClientSecret
import ru.abdulkhalikow.core_utils.JamendoRedirectUrl
import ru.abdulkhalikow.feature_auth_api.AuthProvider
import ru.abdulkhalikow.feature_auth_impl.BuildConfig
import ru.abdulkhalikow.feature_auth_impl.data.AuthInterceptorImpl
import ru.abdulkhalikow.feature_auth_impl.data.AuthProviderImpl
import ru.abdulkhalikow.feature_auth_impl.data.JamendoAuthApi
import javax.inject.Singleton

@Module
object AuthModule {

    @Provides
    @Singleton
    @JamendoClientId
    fun provideJamendoClientId(): String = BuildConfig.JAMENDO_CLIENT_ID

    @Provides
    @Singleton
    @JamendoClientSecret
    fun provideJamendoClientSecret(): String = BuildConfig.JAMENDO_CLIENT_SECRET

    @Provides
    @Singleton
    @JamendoRedirectUrl
    fun provideJamendoRedirectUrl(): String = BuildConfig.JAMENDO_REDIRECT_URL

    @Provides
    @Singleton
    fun provideJamendoAuthApi(): JamendoAuthApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://api.jamendo.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JamendoAuthApi::class.java)
    }
}

@Module
interface AuthBindModule {

    @Binds
    @Singleton
    @AuthInterceptor
    fun bindsAuthInterceptor(impl: AuthInterceptorImpl): Interceptor

    @Binds
    @Singleton
    fun provideAuthProvider(impl: AuthProviderImpl): AuthProvider
}