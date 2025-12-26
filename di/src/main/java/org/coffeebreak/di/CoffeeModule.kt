package org.coffeebreak.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.repository.CoffeeRepositoryImpl
import org.coffeebreak.domain.repository.CoffeeRepository
import org.coffeebreak.domain.usecase.coffe.GetCoffeeByIdUseCase
import org.coffeebreak.domain.usecase.coffe.GetCoffeesUseCase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoffeeModule {
    @Provides
    @Singleton
    fun provideCoffeeRepository(): CoffeeRepository = CoffeeRepositoryImpl()


    @Provides
    @Singleton
    fun provideGetCoffeeUseCase(
        repo: CoffeeRepository )  = GetCoffeesUseCase(repo)

    @Provides
    @Singleton
    fun provideGetCoffeeByIdUseCase(
        repo: CoffeeRepository
    ) = GetCoffeeByIdUseCase(repo)
}