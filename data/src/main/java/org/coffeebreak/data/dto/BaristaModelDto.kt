package org.coffeebreak.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.coffeebreak.domain.model.BaristaModel

@Serializable
data class BaristaModelDto(

    val id: String,
    val name: String,
    val skill: String,
    val status: Boolean,
    @SerialName("avatar_url") val avatarUrl: String
)

fun BaristaModelDto.toDomain(): BaristaModel = (
        BaristaModel(
            id = id,
            name = name,
            skill = skill,
            status = status,
            avatarUrl = avatarUrl
        )

        )