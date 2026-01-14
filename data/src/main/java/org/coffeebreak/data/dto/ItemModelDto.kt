package org.coffeebreak.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.coffeebreak.domain.model.ItemModel

@Serializable
data class ItemModelDto(
    val id: String,
    val title: String,
    val description: String,
    @SerialName("image_url") val imageUrl: String,
    val category: String
)

fun ItemModelDto.toDomain(): ItemModel = (
        ItemModel(
            id = id,
            title = title,
            description = description,
            imageUrl = imageUrl,
            category = category
        )
        )