package org.coffeebreak.domain.usecase.session

import org.coffeebreak.domain.repository.SessionRepository

class LogOutUseCase(private val repo: SessionRepository) {
    suspend fun execute() = repo.clearSession()
}