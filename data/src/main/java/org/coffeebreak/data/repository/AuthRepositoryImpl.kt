package org.coffeebreak.data.repository

import android.util.Log
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import org.coffeebreak.data.data_source.InitSupabaseClient.client
import org.coffeebreak.data.data_source.local.dao.UserDao
import org.coffeebreak.data.dto.toDto
import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.repository.AuthRepository
import org.coffeebreak.domain.utils.CustomResult

class AuthRepositoryImpl(
    private val userDao: UserDao

) : AuthRepository {
    override suspend fun signInWithGoogle(): Result<Unit> {
        return runCatching {

        }
    }

    override suspend fun signUp(user: UserModel): CustomResult<Unit> {
        return try {
            client.auth.signUpWith(Email) {
                this.email = user.email
                this.password = user.password
            }
            val id = client.auth.currentUserOrNull()
            userDao.insertUserData(user.toDto(id!!.id))
            CustomResult.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            CustomResult.Error(e.message!!)
        }
    }

    override suspend fun signIn(): CustomResult<Unit> {
        TODO("Not yet implemented")
    }
}