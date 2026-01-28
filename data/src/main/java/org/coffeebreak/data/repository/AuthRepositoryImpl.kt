package org.coffeebreak.data.repository

import android.util.Log
import io.github.jan.supabase.compose.auth.composable.GoogleDialogType
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.gotrue.providers.builtin.OTP
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.data.dto.toDto
import org.coffeebreak.domain.model.SessionModel
import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.repository.AuthRepository
import org.coffeebreak.domain.repository.SessionRepository
import org.coffeebreak.domain.utils.CustomResult
import kotlin.time.ExperimentalTime


class AuthRepositoryImpl(
    private val userDao: UserDao,
    private val sessionRepository: SessionRepository

) : AuthRepository {
    override suspend fun signInWithGoogle(): Result<Unit> {
//        return Result.success(Unit)
//        val t = client.auth.signInWith(Google) {
//
//        }
        return try {
            val t = client.composeAuth.rememberSignInWithGoogle(
                type = GoogleDialogType.BOTTOM_SHEET,
                onResult = {
                    Log.e("TAG", "LoginScreen: $it")
                }
            ) {
                Log.e("TAG", "LoginScreen: Fallback")
            }
            val session = client.auth.currentSessionOrNull()
            session?.let {
                sessionRepository.saveSession(
                    SessionModel(
                        userId = it.user!!.id,
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        expiresAt = it.expiresAt.epochSeconds
                    )
                )
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun signUp(user: UserModel): CustomResult<Unit> {
        return try {
            client.auth.signUpWith(Email) {
                this.email = user.email
                this.password = user.password
            }
            val id = client.auth.currentUserOrNull()

            val session = client.auth.currentSessionOrNull()
            session?.let {
                sessionRepository.saveSession(
                    SessionModel(
                        userId = it.user!!.id,
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        expiresAt = it.expiresAt.epochSeconds
                    )
                )
            }

            userDao.insertUserData(user.toDto(id!!.id))
            CustomResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            CustomResult.Error(e.message!!)
        }
    }

    @OptIn(ExperimentalTime::class)
    override suspend fun signIn(email: String, password: String): CustomResult<Unit> {
        return try {
            val res = client.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            val session = client.auth.currentSessionOrNull()
            session?.let {
                sessionRepository.saveSession(
                    SessionModel(
                        userId = it.user!!.id,
                        accessToken = it.accessToken,
                        refreshToken = it.refreshToken,
                        expiresAt = it.expiresAt.epochSeconds
                    )
                )
            }
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun sendOTP(email: String): CustomResult<Unit> {
        return try {
            val response = client.auth.resetPasswordForEmail(email)
            CustomResult.Success(Unit)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun checkOTP(otp: String): CustomResult<Unit> {
        val email = userDao.getUserById(client.auth.currentUserOrNull()!!.id)!!.email

        return try {
            val res = client.auth.verifyEmailOtp(OtpType.Email.EMAIL, email, otp)
            CustomResult.Success(res)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun resetPassword(password: String): CustomResult<Unit> {
        return try {
            client.auth.updateUser {
                this.password = password
            }
            CustomResult.Success(Unit)
        } catch (e: Exception) {
            CustomResult.Error(e.message!!)
        }
    }
}
