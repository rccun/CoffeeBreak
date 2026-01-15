package org.coffeebreak.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.coffeebreak.data.ai.CoffeeAIRepositoryImpl
import org.coffeebreak.data.ai.CoffeeModelRunner
import org.coffeebreak.data.mapper.CoffeePromptMapper
import org.coffeebreak.domain.repository.CoffeeAIRepository
import org.coffeebreak.domain.usecase.coffee.GetCoffeeAIUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AIModule {

    @Provides
    @Singleton
    fun provideCoffeeModelRunner(
        @ApplicationContext context: Context
    ): CoffeeModelRunner = CoffeeModelRunner(context)

    @Provides
    @Singleton
    fun provideCoffeePromptMapper(): CoffeePromptMapper = CoffeePromptMapper()

    @Provides
    @Singleton
    fun provideCoffeeAIRepository(
        runner: CoffeeModelRunner,
        mapper: CoffeePromptMapper
    ): CoffeeAIRepository =
        CoffeeAIRepositoryImpl(runner, mapper)

    @Provides
    @Singleton
    fun provideGetCoffeeAIUseCase(
        repo: CoffeeAIRepository
    ) = GetCoffeeAIUseCase(repo)

}