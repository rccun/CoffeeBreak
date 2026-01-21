package org.coffeebreak.domain.usecase.session

import org.coffeebreak.domain.model.SessionModel
import org.coffeebreak.domain.repository.SessionRepository

class GetActiveSessionUseCase(
    private val repo: SessionRepository
) {
    suspend fun execute() = repo.getSession()

}