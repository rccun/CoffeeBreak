package org.coffeebreak.domain.model

data class SessionModel(
val userId: String,
val accessToken: String,
val refreshToken: String,
val expiresAt: Long
)
