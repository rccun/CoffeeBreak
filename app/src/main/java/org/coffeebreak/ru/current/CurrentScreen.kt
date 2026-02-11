package org.coffeebreak.ru.current

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import org.coffeebreak.ru.common.MyTopAppBar
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3
import org.coffeebreak.ru.theme.lineColor

@Composable
fun CurrentScreen(navController: NavController, viewModel: CurrentViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column() {

        Spacer(Modifier.height(21.dp))
        Text(
            "Текущий заказ №002",
            color = MainTheme.colorScheme.titleText,
            style = MainTheme.typography.labelMedium,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(lineColor)
        )
        Spacer(Modifier.height(21.dp))
        if (state.isLoading) {
            Text("Downloading")
        } else {
            val coffee = state.coffee!!
            val order = state.order!!

            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(Modifier.width(22.dp))
                MyAsyncImage(
                    modifier = Modifier
                        .heightIn(max = 44.dp),
                    imageUrl = coffee.imageUrl,
                    isMaxWidth = false,
                    contentScale = ContentScale.FillHeight
                )
                Spacer(Modifier.width(18.dp))
                Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            coffee.title,
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 14.sp,
                            color = blue3
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            "x${order.count}",
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 10.sp,
                            color = MainTheme.colorScheme.timeCartItem
                        )
                    }
                    Text(
                        state.time,
                        style = MainTheme.typography.chooseBarista,
                        fontSize = 10.sp,
                        color = MainTheme.colorScheme.timeCartItem
                    )
                }
                Spacer(Modifier.weight(1f))
                Text(
                    "${order.totalCoast} P", color = blue3,
                    style = MainTheme.typography.chooseBarista,
                )
                Spacer(Modifier.width(27.dp))

            }
            Spacer(Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .padding(start = 10.dp, end = 4.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(lineColor)
            )
        }
    }
}
