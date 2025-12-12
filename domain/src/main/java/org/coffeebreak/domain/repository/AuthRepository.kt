package org.coffeebreak.domain.repository

interface AuthRepository {
    suspend fun signInWithGoogle(): Result<Unit>
}