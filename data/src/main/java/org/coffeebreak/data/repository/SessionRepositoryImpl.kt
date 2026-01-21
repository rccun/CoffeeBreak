package org.coffeebreak.data.repository

import org.coffeebreak.data.utils.SecureStorage
import org.coffeebreak.domain.model.SessionModel
import org.coffeebreak.domain.repository.SessionRepository

class SessionRepositoryImpl(
    private val secureStorage: SecureStorage
) : SessionRepository {

    private var cachedSession: SessionModel? = null

    override suspend fun getSession(): SessionModel? {
        if (cachedSession != null) return cachedSession

        cachedSession = secureStorage.loadSession()
        return cachedSession
    }

    override suspend fun saveSession(session: SessionModel) {
        cachedSession = session
        secureStorage.saveSession(session)
    }

    override suspend fun clearSession() {
        cachedSession = null
        secureStorage.clear()
    }
}