package org.coffeebreak.domain.usecase.user

import org.coffeebreak.domain.repository.UserRepository

class UpdateAddressUseCase(
    private val repo: UserRepository
) {
    suspend fun execute(address: String) = repo.updateAddress(address)
}