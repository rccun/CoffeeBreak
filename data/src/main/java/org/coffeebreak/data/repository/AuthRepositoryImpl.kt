package org.coffeebreak.data.repository

import android.util.Log
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.Google
import io.github.jan.supabase.compose.auth.composable.GoogleDialogType
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth
import org.coffeebreak.data.data_source.InitSupabaseClient
import org.coffeebreak.domain.repository.AuthRepository

class AuthRepositoryImpl() : AuthRepository {
    override suspend fun signInWithGoogle(): Result<Unit> {
        return runCatching {

        }
    }
}