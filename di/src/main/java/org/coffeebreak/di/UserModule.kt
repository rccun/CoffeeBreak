package org.coffeebreak.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.data.repository.UserRepositoryImpl
import org.coffeebreak.domain.repository.UserRepository
import org.coffeebreak.domain.usecase.user.GetUserByIdUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(userDao)


    @Provides
    @Singleton
    fun provideGetUserByIdUseCase(
        repo: UserRepository
    ) = GetUserByIdUseCase(repo)
}