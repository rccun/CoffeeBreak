package org.coffeebreak.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.repository.OrderRepositoryImpl
import org.coffeebreak.domain.repository.OrderRepository
import org.coffeebreak.domain.usecase.barista.GetBaristasUseCase
import org.coffeebreak.domain.usecase.items.GetItemsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderModule {
    @Provides
    @Singleton
    fun provideOrderRepository(): OrderRepository = OrderRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetBaristasUseCase(
        repo: OrderRepository
    ) = GetBaristasUseCase(repo)

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repo: OrderRepository) = GetItemsUseCase(repo)


}