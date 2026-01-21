package org.coffeebreak.data.utils

import org.coffeebreak.domain.model.SessionModel

interface SecureStorage {
    suspend fun saveSession(session: SessionModel)
    suspend fun loadSession(): SessionModel?
    suspend fun clear()
}