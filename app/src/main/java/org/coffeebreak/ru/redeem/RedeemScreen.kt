package org.coffeebreak.ru.redeem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import org.coffeebreak.domain.model.CoffeeModel
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.MyAsyncImage
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.common.MyTopAppBar
import org.coffeebreak.ru.menu.CoffeeCard
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3

@Composable
fun RedeemScreen(navController: NavController, viewModel: RedeemViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column() {
        MyTopAppBar(
            stringResource(R.string.pay_points),
            isBack = true,
            isCart = false,
            onBackClick = { navController.popBackStack() }) {

            Spacer(Modifier.height(15.dp))
            repeat(3) { i ->
//                val coffee = state.coffees[i]
                val coffee = CoffeeModel(
                    "",
                    "Американо",
                    "https://wsmwattyuklbdszczfpz.supabase.co/storage/v1/object/public/coffees/latte.png",
                    "04.07",
                    120
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MyAsyncImage(
                        modifier = Modifier
//                        .padding(vertical = 12.dp)
                            .heightIn(max = 57.dp),
                        imageUrl = coffee.imageUrl,
                        isMaxWidth = false,
                        contentScale = ContentScale.FillHeight
                    )

                    Spacer(Modifier.width(18.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            coffee.title,
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 14.sp,
                            color = blue3
                        )
                        Text(
                            "Действительно до ${coffee.period}",
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 10.sp, color = MainTheme.colorScheme.authMedium
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(blue3)
                    ) {
                        Text(
                            "${coffee.coast} баллов",
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 10.sp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)
                        )
                    }
                }
                Spacer(Modifier.height(30.dp))
            }
        }
    }
}