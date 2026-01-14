package org.coffeebreak.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.data_source.local.AppDatabase
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.data.repository.AuthRepositoryImpl
import org.coffeebreak.domain.repository.AuthRepository
import org.coffeebreak.domain.usecase.auth.SignUpUseCase
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        AppDatabase.createDatabase(context).userDao

    @Provides
    @Singleton
    fun provideAuthRepository(
        userDao: UserDao
    ): AuthRepository = AuthRepositoryImpl(userDao)

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        repo: AuthRepository
    ) = SignUpUseCase(repo)
}