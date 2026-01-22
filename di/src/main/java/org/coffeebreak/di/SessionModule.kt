package org.coffeebreak.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.repository.QRRepositoryImpl
import org.coffeebreak.data.repository.SecureStorageImpl
import org.coffeebreak.data.repository.SessionRepositoryImpl
import org.coffeebreak.data.utils.SecureStorage
import org.coffeebreak.domain.repository.QRRepository
import org.coffeebreak.domain.repository.SessionRepository
import org.coffeebreak.domain.usecase.session.GetActiveSessionUseCase
import org.coffeebreak.domain.usecase.session.LogOutUseCase
import org.coffeebreak.domain.utils.GenerateQR
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SessionModule {

    @Provides
    @Singleton
    fun provideSecureStorage(
        @ApplicationContext context: Context
    ): SecureStorage = SecureStorageImpl(context)

    @Provides
    @Singleton
    fun provideSessionRepository(
        secureStorage: SecureStorage
    ): SessionRepository =
        SessionRepositoryImpl(secureStorage)

    @Provides
    fun provideGetActiveSessionUseCase(
        repository: SessionRepository
    ) = GetActiveSessionUseCase(repository)

    @Provides
    @Singleton
    fun provideLogOutUseCase(repo: SessionRepository) = LogOutUseCase(repo)


    // QR
    @Provides
    @Singleton
    fun provideQRRepository(): QRRepository = QRRepositoryImpl()

    @Provides
    @Singleton
    fun provideGenerateQR(repo: QRRepository) = GenerateQR(repo)
}
