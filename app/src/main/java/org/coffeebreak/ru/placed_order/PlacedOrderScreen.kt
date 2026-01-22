package org.coffeebreak.ru.placed_order

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun PlacedOrderScreen(navController: NavController, viewModel: PlacedOrderViewModel = hiltViewModel()) {
    val info = viewModel.info.collectAsState().value
    Log.e("TAG123", "${info.first}: ");
    val isTimeOut = viewModel.isTimeOut.collectAsState().value
    LaunchedEffect(isTimeOut) {
        if (isTimeOut) {
            navController.navigate(Route.Menu(true))
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        MyIcon(
            icon = R.drawable.back,
            tintColor = MainTheme.colorScheme.icon,
            modifier = Modifier
                .align(
                    Alignment.TopStart
                )
                .padding(start = 30.dp, top = 27.dp)
        ) {
            navController.navigate(Route.Menu(true))
        }

        Row(
            modifier = Modifier.align(
                Alignment.Center
            )
        ) {
            Spacer(Modifier.weight(1f))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(4.73f)// 4.73
            ) {
                MyIcon(icon = R.drawable.placed, tintColor = MainTheme.colorScheme.icon)
                Text(
                    stringResource(R.string.placed),
                    modifier = Modifier.padding(top = 32.dp, bottom = 22.dp),
                    style = MainTheme.typography.bodyLarge,
                    color = MainTheme.colorScheme.placed,
                    textAlign = TextAlign.Center

                )// l textColor d b2
                Text(
                    info.first + stringResource(R.string.order_placed),
                    color = MainTheme.colorScheme.orderPlaced,
                    style = MainTheme.typography.titleMedium
                ) // lightgray b3
                Text(
                    "Заказ будет готов сегодня\n" +
                            "к ${info.second.dropLast(3)} по адресу \n" +
                            "г. Оренбург, ${info.third}",
                    modifier = Modifier.padding(vertical = 22.dp),
                    color = MainTheme.colorScheme.orderPlacedTime,
                    style = MainTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Text(
                    stringResource(R.string.qr_code), color = MainTheme.colorScheme.orderPlaced,
                    style = MainTheme.typography.titleMedium,
                    textAlign = TextAlign.Center

                )
            }
            Spacer(Modifier.weight(1f))

        }
    }
}