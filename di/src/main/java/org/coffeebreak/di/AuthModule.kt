package org.coffeebreak.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.auth.Auth
import org.coffeebreak.data.data_source.local.AppDatabase
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.data.repository.AuthRepositoryImpl
import org.coffeebreak.data.utils.EmailValidatorImpl
import org.coffeebreak.domain.repository.AuthRepository
import org.coffeebreak.domain.repository.SessionRepository
import org.coffeebreak.domain.usecase.auth.CheckOTPUseCase
import org.coffeebreak.domain.usecase.auth.EmailUseCase
import org.coffeebreak.domain.usecase.auth.PasswordUseCase
import org.coffeebreak.domain.usecase.auth.ResetPasswordUseCase
import org.coffeebreak.domain.usecase.auth.SendOTPUseCase
import org.coffeebreak.domain.usecase.auth.SignInUseCase
import org.coffeebreak.domain.usecase.auth.SignInWithGoogleUseCase
import org.coffeebreak.domain.usecase.auth.SignUpUseCase
import org.coffeebreak.domain.usecase.auth.ValidateUseCase
import org.coffeebreak.domain.utils.EmailValidator
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
        userDao: UserDao,
        repo: SessionRepository
    ): AuthRepository = AuthRepositoryImpl(userDao, repo)

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        repo: AuthRepository
    ) = SignUpUseCase(repo)

    @Provides
    @Singleton
    fun provideSignInUseCase(
        repo: AuthRepository
    ) = SignInUseCase(repo)

    @Provides
    @Singleton
    fun provideSendOTPUseCase(repo: AuthRepository) = SendOTPUseCase(repo)

    @Provides
    @Singleton
    fun provideCheckOTPUseCase(repo: AuthRepository) = CheckOTPUseCase(repo)

    @Provides
    @Singleton
    fun provideResetPasswordUseCase(repo: AuthRepository) = ResetPasswordUseCase(repo)

    @Provides
    @Singleton
    fun providePasswordUseCase() = PasswordUseCase()

    @Provides
    @Singleton
    fun provideEmailValidator(): EmailValidator = EmailValidatorImpl()

    @Provides
    @Singleton
    fun provideEmailUseCase(repo: EmailValidator) = EmailUseCase(repo)

    @Provides
    @Singleton
    fun provideValidateUseCase(emailUseCase: EmailUseCase, passwordUseCase: PasswordUseCase) =
        ValidateUseCase(passwordUseCase, emailUseCase)

    @Provides
    @Singleton
    fun provideSignInWithGoogleUseCase(repo: AuthRepository) = SignInWithGoogleUseCase(repo)
}