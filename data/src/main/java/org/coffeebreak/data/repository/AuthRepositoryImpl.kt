package org.coffeebreak.data.repository

import org.coffeebreak.domain.repository.AuthRepository

class AuthRepositoryImpl() : AuthRepository {
    override suspend fun signInWithGoogle(): Result<Unit> {
        return runCatching {

        }
    }
}