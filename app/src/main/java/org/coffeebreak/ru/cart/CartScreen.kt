package org.coffeebreak.ru.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.CartItem
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.lineColor

@Composable
fun CartScreen(navController: NavController, viewModel: CartViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(21.dp))

        Text(
            stringResource(R.string.order_history),
            color = MainTheme.colorScheme.titleText,
            style = MainTheme.typography.labelMedium,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(34.dp))

        Column(modifier = Modifier.padding(horizontal = 25.dp)) {
            if (state.isLoading) {
                Text("Downloading")
            } else {
                state.data.forEach { i ->
                    CartItem(
                        imageUrl = i.imageUrl,
                        coffeeTitle = i.coffeeTitle,
                        address = i.address,
                        date = i.date,
                        createdAt = i.createdAt,
                        time = i.time,
                        coast = i.coast,
                        isButton = true
                    )
                }
            }
        }
    }
}