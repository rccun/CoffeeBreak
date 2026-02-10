package org.coffeebreak.ru.current

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CurrentScreen(navController: NavController, orderId: String) {
    Column() {

        Text(orderId)
    }
}