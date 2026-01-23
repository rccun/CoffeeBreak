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
            stringResource(R.string.my_order),
            color = MainTheme.colorScheme.titleText,
            style = MainTheme.typography.labelMedium,
            fontSize = 16.sp
        )
        Spacer(Modifier.height(26.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        )
        {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(IntrinsicSize.Max)
            ) {
                Text(
                    "Текущий",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { viewModel.onEvent(CartEvents.OnPageClick) },
//                    style = MainTheme.typography.bodyLarge,
                    color = if (state.page == 0) {
                        MainTheme.colorScheme.titleText
                    } else {
                        MainTheme.colorScheme.unactiveCartTab // b2 D8D8D8
                    },
                )
                Box(
                    Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .height(4.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))// 16 round
                        .background(
                            if (state.page == 0) { // 304857 blue3
                                MainTheme.colorScheme.cartTabLine
                            } else {
                                Color.Transparent
                            },
                        )
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(IntrinsicSize.Max)

            ) {
                Text(
                    "История",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable { viewModel.onEvent(CartEvents.OnPageClick) },

//                    style = MainTheme.typography.bodyLarge,
                    color = if (state.page != 0) {
                        MainTheme.colorScheme.titleText
                    } else {
                        MainTheme.colorScheme.unactiveCartTab
                    },

                    )
                Box(
                    Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()


                        .height(4.dp)
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(
                            if (state.page != 0) {
                                MainTheme.colorScheme.cartTabLine
                            } else {
                                Color.Transparent
                            },
                        )
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
//                .align(Alignment.BottomCenter)
                .background(lineColor) // linec
        )


        Column(modifier = Modifier.padding(horizontal = 25.dp)) {
            if (state.isLoading) {
                Text("Down")
            } else {
                when (state.page) {
                    0 -> {

                        state.data.forEach { i ->
                            CartItem(
                                imageUrl = i.imageUrl,
                                coffeeTitle = i.coffeeTitle,
                                address = i.address,
                                date = i.date,
                                createdAt = i.createdAt,
                                time = i.time,
                                coast = i.coast
                            )
                        }
                    }

                    1 -> {

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
    }
}