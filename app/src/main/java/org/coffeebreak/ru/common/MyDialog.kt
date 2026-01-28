package org.coffeebreak.ru.common

import androidx.compose.foundation.clickable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MyDialog(
    title: String,
    text: String,
    show: Boolean,
    onDismissRequest: () -> Unit,
) {
    if (show) {
        AlertDialog(
            onDismissRequest = { onDismissRequest() },
            title = { Text(title) },
            text = { Text(text) },
            confirmButton = { Text("OK", modifier = Modifier.clickable { onDismissRequest() }) },

            )
    }
}