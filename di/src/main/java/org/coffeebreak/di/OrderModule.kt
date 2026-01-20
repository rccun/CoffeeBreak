package org.coffeebreak.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.data_source.local.AppDatabase
import org.coffeebreak.data.data_source.local.dao.OrderDao
import org.coffeebreak.data.repository.OrderRepositoryImpl
import org.coffeebreak.domain.repository.OrderRepository
import org.coffeebreak.domain.usecase.barista.GetBaristasUseCase
import org.coffeebreak.domain.usecase.items.GetItemsByCategoryUseCase
import org.coffeebreak.domain.usecase.order.OrderUseCase
import org.coffeebreak.domain.usecase.order.SetOrderUseCase
import org.coffeebreak.domain.usecase.order.SetPreOrderUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderModule {


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context) =
        AppDatabase.createDatabase(context).orderDao

    @Provides
    @Singleton
    fun provideOrderRepository(
        orderDao: OrderDao
    ): OrderRepository = OrderRepositoryImpl(orderDao)

    @Provides
    @Singleton
    fun provideGetBaristasUseCase(
        repo: OrderRepository
    ) = GetBaristasUseCase(repo)

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repo: OrderRepository) = GetItemsByCategoryUseCase(repo)

    @Provides
    @Singleton
    fun provideSetPreOrderUseCase(repo: OrderRepository) = SetPreOrderUseCase(repo)

    @Provides
    @Singleton
    fun provideOrderUseCase() = OrderUseCase()

    @Provides
    @Singleton
    fun provideSetOrderUseCase(repo: OrderRepository) = SetOrderUseCase(repo)


}