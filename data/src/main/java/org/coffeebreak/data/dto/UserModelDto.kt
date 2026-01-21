package org.coffeebreak.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import org.coffeebreak.domain.model.UserModel

@Entity(tableName = "users")
@Serializable
data class UserModelDto(
    @PrimaryKey val id: String,
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val address: String? = null
)

fun UserModel.toDto(id: String): UserModelDto = (
        UserModelDto(
            id = id,
            email = email,
            password = password,
            name = name,
            phone = phone,
            address = address
        )
        )

fun UserModelDto.toDomain(): UserModel = (
        UserModel(
            email = email,
            password = password,
            phone = phone,
            name = name,
            address = address

        )
        )