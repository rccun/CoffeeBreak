package org.coffeebreak.domain.model

data class BaristaModel(
    val id: String,
    val name: String,
    val skill: String,
    val status: Boolean,
    val avatarUrl: String
)
