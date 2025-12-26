package org.coffeebreak.ru.create_order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.MyAsyncImage
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.grayWhite

@Composable
fun CreateOrderScreen(navController: NavController, id: String, viewModel: CreateOrderViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(Unit) {
        viewModel.loadCoffee(id)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, ) {
            MyIcon(icon = R.drawable.back, tintColor = MainTheme.colorScheme.icon)
            Text(
                stringResource(R.string.order),
                color = MainTheme.colorScheme.titleText,
                style = MainTheme.typography.labelMedium,
                fontSize = 16.sp
            )
            MyIcon(icon = R.drawable.cart, tintColor = MainTheme.colorScheme.icon)
        }
        if (state.isLoading) {
            Text("Загрузка")
        } else {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(grayWhite)
                    .fillMaxWidth()
            ) {
                MyAsyncImage(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .heightIn(max = 121.dp),
                    imageUrl = state.coffee!!.imageUrl,
                    contentScale = ContentScale.FillHeight
                )
            }
            Row {
                Text(state.coffee!!.title, color = Color.White)
                Row() {

                }
            }
        }
    }

}