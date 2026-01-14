package org.coffeebreak.domain.usecase.user

import org.coffeebreak.domain.repository.UserRepository

class GetUserByIdUseCase(
private val repo: UserRepository
) {
    suspend fun execute(id: String?) = repo.getUserById(id)
}