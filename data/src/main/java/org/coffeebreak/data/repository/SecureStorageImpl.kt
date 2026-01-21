package org.coffeebreak.data.repository

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.coffeebreak.data.utils.SecureStorage
import org.coffeebreak.domain.model.SessionModel

class SecureStorageImpl(
    context: Context
) : SecureStorage {

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "secure_session",
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override suspend fun saveSession(session: SessionModel) {
        prefs.edit()
            .putString("user_id", session.userId)
            .putString("access_token", session.accessToken)
            .putString("refresh_token", session.refreshToken)
            .putLong("expires_at", session.expiresAt)
            .apply()
    }

    override suspend fun loadSession(): SessionModel? {
        val userId = prefs.getString("user_id", null) ?: return null

        return SessionModel(
            userId = userId,
            accessToken = prefs.getString("access_token", "") ?: "",
            refreshToken = prefs.getString("refresh_token", "") ?: "",
            expiresAt = prefs.getLong("expires_at", 0L)
        )
    }

    override suspend fun clear() {
        prefs.edit().clear().apply()
    }
}
