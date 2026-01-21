package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.SessionModel

interface SessionRepository {
    suspend fun getSession(): SessionModel?
    suspend fun saveSession(session: SessionModel)
    suspend fun clearSession()
}