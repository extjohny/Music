package ru.abdulkhalikow.music.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.abdulkhalikow.core_network.NetworkModule
import ru.abdulkhalikow.feature_auth_impl.di.AuthBindModule
import ru.abdulkhalikow.feature_auth_impl.di.AuthModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        AuthModule::class,
        AuthBindModule::class,
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(app: Application): Builder

        fun build(): AppComponent
    }
}
