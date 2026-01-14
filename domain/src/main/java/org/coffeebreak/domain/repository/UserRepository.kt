package org.coffeebreak.domain.repository

import org.coffeebreak.domain.model.UserModel
import org.coffeebreak.domain.utils.CustomResult

interface UserRepository {
    suspend fun getUserById(id: String?): CustomResult<UserModel>
}