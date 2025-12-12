package org.coffeebreak.data.data_source

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.OAuthProvider
import io.github.jan.supabase.compose.auth.ComposeAuth
import io.github.jan.supabase.compose.auth.googleNativeLogin
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage

object InitSupabaseClient {
    val client = createSupabaseClient(
        "https://wsmwattyuklbdszczfpz.supabase.co",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6IndzbXdhdHR5dWtsYmRzemN6ZnB6Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjUyNzI0ODksImV4cCI6MjA4MDg0ODQ4OX0.pJB_X4Wq9v_gGtn3kJp3iYPuRcnzSN6wXNvFWLsYDoM"
    ) {
        install(Postgrest)
        install(Realtime)
        install(Storage)
        install(Auth)
        install(ComposeAuth) {
            googleNativeLogin(
                "1063524495116-o8ck47mj0lg6h9fsrhev0heutuue3hr4.apps.googleusercontent.com"
            )
        }
    }
}