package org.coffeebreak.ru.create_order

import android.app.TimePickerDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.BoxItem
import org.coffeebreak.ru.common.MyAsyncImage
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.common.OrderIcon
import org.coffeebreak.ru.common.OrderTimePicker
import org.coffeebreak.ru.common.RowItem
import org.coffeebreak.ru.login.MyIcon
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.b1
import org.coffeebreak.ru.theme.grayD8
import org.coffeebreak.ru.theme.grayWhite
import org.coffeebreak.ru.theme.green1
import org.coffeebreak.ru.theme.green2
import org.coffeebreak.ru.theme.lineColor
import java.util.Calendar

@Composable
fun CreateOrderScreen(
    navController: NavController,
    id: String,
    viewModel: CreateOrderViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    LaunchedEffect(Unit) {
        viewModel.loadCoffee(id)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
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
            )
            {
                MyAsyncImage(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                        .heightIn(max = 121.dp),
                    imageUrl = state.coffee!!.imageUrl,
                    contentScale = ContentScale.FillHeight
                )
            }
            RowItem(state.coffee!!.title)
            {
                Row(
                    modifier = Modifier
                        .border(
                            1.2.dp,
                            MainTheme.colorScheme.unactiveBottomIcon,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(vertical = 9.dp, horizontal = 12.dp)
                )
                {
                    Text(
                        "-", style = MainTheme.typography.displaySmall,
                        color = MainTheme.colorScheme.icon,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(CreateOrderEvents.OnDelClick)
                        }
                    )
                    Text(
                        "${state.count}", style = MainTheme.typography.displaySmall,
                        color = MainTheme.colorScheme.icon,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Text(
                        "+", style = MainTheme.typography.displaySmall,
                        color = MainTheme.colorScheme.icon,
                        modifier = Modifier.clickable {
                            viewModel.onEvent(CreateOrderEvents.OnAddClick)
                        }
                    )
                }
            }
            RowItem(text = stringResource(R.string.ristretto))
            {
                BoxItem(state.ristrettoOne, stringResource(R.string.one)) {
                    viewModel.onEvent(CreateOrderEvents.OnRisChange)
                }
                Spacer(Modifier.width(8.dp))
                BoxItem(!state.ristrettoOne, stringResource(R.string.two)) {
                    viewModel.onEvent(CreateOrderEvents.OnRisChange)
                }
            }
            RowItem(stringResource(R.string.pickup))
            {
                Row(verticalAlignment = Alignment.Bottom) {
                    OrderIcon(R.drawable.pickup_place, state.pickupPlace == 0) {
                        viewModel.onEvent(CreateOrderEvents.OnPickupChange)
                    }
                    Spacer(Modifier.width(30.dp))
                    OrderIcon(R.drawable.pickup_takeaway, state.pickupPlace == 1) {
                        viewModel.onEvent(CreateOrderEvents.OnPickupChange)
                    }
                }
            }
            RowItem(stringResource(R.string.volume))
            {
                Row(verticalAlignment = Alignment.Bottom) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OrderIcon(R.drawable.small_volume, state.volume == 0) {
                            viewModel.onEvent(CreateOrderEvents.OnSmallClick)
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "250", style = MainTheme.typography.displaySmall, color =
                                if (state.volume != 0) {
                                    MainTheme.colorScheme.unactiveBottomIcon
                                } else {
                                    MainTheme.colorScheme.activeOrderPickup
                                }
                        )
                    }
                    Spacer(Modifier.width(27.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OrderIcon(R.drawable.medium_volume, state.volume == 1) {
                            viewModel.onEvent(CreateOrderEvents.OnMediumClick)
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "350", style = MainTheme.typography.displaySmall, color =
                                if (state.volume != 1) {
                                    MainTheme.colorScheme.unactiveBottomIcon
                                } else {
                                    MainTheme.colorScheme.activeOrderPickup
                                }
                        )
                    }
                    Spacer(Modifier.width(27.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OrderIcon(R.drawable.large_volume, state.volume == 2) {
                            viewModel.onEvent(CreateOrderEvents.OnLargeClick)
                        }
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "450", style = MainTheme.typography.displaySmall, color =
                                if (state.volume != 2) {
                                    MainTheme.colorScheme.unactiveBottomIcon
                                } else {
                                    MainTheme.colorScheme.activeOrderPickup
                                }
                        )
                    }
                }
            }
            RowItem(stringResource(R.string.specific_time), align = Alignment.Top, isLine = false) {
                Column(horizontalAlignment = Alignment.End) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(53.dp))
                            .background(
                                if (state.isSpecificTime) {
                                    green1
                                } else {
                                    grayD8
                                }
                            )
                            .height(30.dp)
                            .width(51.dp)
                            .padding(horizontal = 2.dp)
                            .clickable {
                                viewModel.onEvent(CreateOrderEvents.OnTimeSwitch)

                            }
                    )
                    {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White)
                                .size(26.dp)
                                .align(
                                    if (state.isSpecificTime) {
                                        Alignment.CenterEnd
                                    } else {
                                        Alignment.CenterStart
                                    }
                                )
                        )
                    }
                    Spacer(Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color(0x12767680))
                            .padding(horizontal = 15.dp, vertical = 9.dp)
                            .clickable {
                                if (state.isSpecificTime) {
                                    viewModel.onEvent(CreateOrderEvents.OnPickerClick)
                                }
                            }
                    ) {
                        Text(
                            "${state.timeHours} : ${state.timeMinutes}",
                            style = MainTheme.typography.titleSmall,
                            fontSize = 22.sp,
                            color =
                                if (state.isSpecificTime) {
                                    MainTheme.colorScheme.default
                                } else {
                                    grayD8
                                }
                        )
                    }

                }
            }
            Spacer(Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(green2)
                    .padding(vertical = 17.dp, horizontal = 19.dp),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                MyIcon(icon = R.drawable.constructor, tintColor = Color.White)
                Spacer(Modifier.width(10.dp))
                Text(
                    stringResource(R.string.constructor),
                    style = MainTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))
                MyIcon(icon = R.drawable.next2, tintColor = Color.White)
            }
            Spacer(Modifier.height(27.dp))
            Row() {
                Text(
                    stringResource(R.string.sum),
                    style = MainTheme.typography.titleMedium,
                    color = MainTheme.colorScheme.icon
                )
                Spacer(Modifier.weight(1f))
                Text(
                    "100 ₽",
                    style = MainTheme.typography.bodySmall,
                    color = MainTheme.colorScheme.orderCoast
                )
            }
            Spacer(Modifier.height(11.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        MainTheme.colorScheme.orderButton
                    )
                    .clickable {

                    }
                    .padding(vertical = 15.dp),
            )
            {
                Text(
                    stringResource(R.string.next),
                    modifier = Modifier.align(Alignment.Center),
                    style = MainTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }
    }
    OrderTimePicker(
        { h, m ->
            viewModel.onEvent(CreateOrderEvents.OnTimeChange(h, m))
        },
        isShow = state.isTimeInput
    )
}
