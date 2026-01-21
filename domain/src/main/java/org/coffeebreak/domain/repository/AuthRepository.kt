package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.utils.CustomResult

interface AuthRepository {
    suspend fun signInWithGoogle(): Result<Unit>
    suspend fun signUp(user: UserModel): CustomResult<Unit>
    suspend fun signIn(email: String, password: String): CustomResult<Unit>
}