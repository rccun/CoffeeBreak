package org.coffeebreak.ru.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun MyDialog(
    title: String,
    text: String,
    show: Boolean,
    onDismissRequest: () -> Unit,
    ) {
        if (show) {
            AlertDialog(
                onDismissRequest = onDismissRequest,
                title = { Text(title) },
                text = { Text(text) },
                confirmButton = { Text("OK") }
            )
        }
    }