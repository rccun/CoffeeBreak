package org.coffeebreak.ru.construstor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.DownMenu
import org.coffeebreak.ru.common.EncyclopediaItem
import org.coffeebreak.ru.common.MyDialog
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.common.OrderIcon
import org.coffeebreak.ru.common.RowItem
import org.coffeebreak.ru.common.SliderItem
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.grayD8
import org.coffeebreak.ru.theme.green1

@Composable
fun CoffeeConstructorScreen(
    navController: NavController,
    viewModel: CoffeeConstructorViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .background(
                if (state.isMilkItems || state.isSyrupItems) {
                    Color.Black.copy(alpha = 0.16f)
                } else {
                    Color.Transparent
                }
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 25.dp)

        )
        {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MyIcon(icon = R.drawable.back, tintColor = MainTheme.colorScheme.icon)
                Text(
                    stringResource(R.string.constructor),
                    color = MainTheme.colorScheme.titleText,
                    style = MainTheme.typography.labelMedium,
                    fontSize = 16.sp
                )
                MyIcon(icon = R.drawable.cart, tintColor = MainTheme.colorScheme.icon)
            }
            RowItem(stringResource(R.string.choose_barista)) {
                MyIcon(icon = R.drawable.next2, tintColor = Color(0xFF7B6F72)) {
                    navController.navigate(Route.Barista)
                }
            }
            RowItem(stringResource(R.string.type_coffee)) {
                Column(
                    modifier = Modifier
                        .padding(start = 15.dp)
                ) {
                    SliderItem(
                        state.weight, {
                            viewModel.onEvent(CoffeeConstructorEvents.OnSliderChange(it))
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(Modifier.height(10.dp))
                    Row() {
                        Text(
                            stringResource(R.string.arabic),
                            style = MainTheme.typography.displaySmall,
                            color = grayD8
                        )
                        Spacer(Modifier.weight(1f))
                        Text(
                            stringResource(R.string.robusta),
                            style = MainTheme.typography.displaySmall,
                            color = grayD8
                        )
                    }
                }
            }
            RowItem(stringResource(R.string.sort_coffee)) {
                MyIcon(icon = R.drawable.next2, tintColor = MainTheme.colorScheme.consNextIcon) {
                    navController.navigate(Route.Country)
                }
            }
            RowItem(stringResource(R.string.roasting)) {

                Row(verticalAlignment = Alignment.Bottom) {
                    OrderIcon(R.drawable.roasting, state.roasting == 1) {
                        viewModel.onEvent(CoffeeConstructorEvents.OnSmallClick)
                    }
                    Spacer(Modifier.width(22.dp))
                    Row()
                    {
                        OrderIcon(R.drawable.roasting, state.roasting == 2) {

                            viewModel.onEvent(CoffeeConstructorEvents.OnMediumClick)

                        }
                        Spacer(Modifier.width(3.dp))
                        OrderIcon(R.drawable.roasting, state.roasting == 2) {

                            viewModel.onEvent(CoffeeConstructorEvents.OnMediumClick)

                        }
                    }
                    Spacer(Modifier.width(22.dp))
                    Box(

                    ) {

                        OrderIcon(
                            R.drawable.roasting,
                            state.roasting == 3,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(bottom = 26.dp)
                        ) {

                            viewModel.onEvent(CoffeeConstructorEvents.OnLargeClick)

                        }
                        Row(
                            modifier = Modifier.align(Alignment.BottomCenter)

                        ) {
                            OrderIcon(R.drawable.roasting, state.roasting == 3) {
                                viewModel.onEvent(CoffeeConstructorEvents.OnLargeClick)


                            }
                            Spacer(Modifier.width(3.dp))
                            OrderIcon(R.drawable.roasting, state.roasting == 3) {
                                viewModel.onEvent(CoffeeConstructorEvents.OnLargeClick)


                            }
                        }
                    }
                }
            }
            RowItem(stringResource(R.string.grinding)) {
                Row(verticalAlignment = Alignment.Bottom) {
                    OrderIcon(
                        icon = R.drawable.grinding_small, state.grinding == 0
                    ) {
                        viewModel.onEvent(CoffeeConstructorEvents.OnSmallGrindingClick)
                    }
                    Spacer(Modifier.width(44.dp))

                    OrderIcon(
                        icon = R.drawable.grinding_large, state.grinding == 1
                    ) {
                        viewModel.onEvent(CoffeeConstructorEvents.OnLargeGrindingClick)
                    }
                }
            }
            RowItem(stringResource(R.string.milk)) {
                Text(
                    stringResource(R.string.choose),
                    color = MainTheme.colorScheme.icon,
                    style = MainTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            viewModel.onEvent(CoffeeConstructorEvents.OnMilkItemsClick)
                        }
                )
            }
            RowItem(stringResource(R.string.syrup)) {
                Text(
                    stringResource(R.string.choose),
                    color = MainTheme.colorScheme.icon,
                    style = MainTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            viewModel.onEvent(CoffeeConstructorEvents.OnSyrupItemsClick)

                        }
                )
            }

            RowItem(stringResource(R.string.supplements)) {
                MyIcon(icon = R.drawable.next2, tintColor = MainTheme.colorScheme.consNextIcon) {
                    navController.navigate(Route.Supplement)

                }
            }
            RowItem(stringResource(R.string.ice), isLine = false) {
                Row(verticalAlignment = Alignment.Bottom) {
                    OrderIcon(icon = R.drawable.ice0, state.ice == 0) {
                        viewModel.onEvent(CoffeeConstructorEvents.OnIce0Click)
                    }
                    Spacer(Modifier.width(26.dp))

                    OrderIcon(icon = R.drawable.ice, state.ice == 1) {
                        viewModel.onEvent(CoffeeConstructorEvents.OnSmallIceClick)
                    }
                    Spacer(Modifier.width(26.dp))

                    Box() {
                        OrderIcon(
                            icon = R.drawable.ice,
                            state.ice == 2,
                            modifier = Modifier
                                .padding(bottom = 6.dp, end = 15.dp)
                                .align(
                                    Alignment.TopStart
                                )
                        ) {
                            viewModel.onEvent(CoffeeConstructorEvents.OnMediumIceClick)
                        }
                        OrderIcon(
                            icon = R.drawable.ice, state.ice == 2, modifier = Modifier.align(
                                Alignment.BottomEnd
                            )
                        ) {
                            viewModel.onEvent(CoffeeConstructorEvents.OnMediumIceClick)
                        }
                    }
                    Spacer(Modifier.width(26.dp))

                    Box() {
                        OrderIcon(
                            icon = R.drawable.ice, state.ice == 3, modifier = Modifier
                                .align(
                                    Alignment.TopCenter
                                )
                                .padding(bottom = 13.dp)
                        ) {
                            viewModel.onEvent(CoffeeConstructorEvents.OnLargeIceClick)
                        }
                        Row(modifier = Modifier.align(Alignment.BottomCenter)) {
                            OrderIcon(
                                icon = R.drawable.ice,
                                state.ice == 3,
                                modifier = Modifier.padding(end = 1.dp)
                            ) {
                                viewModel.onEvent(CoffeeConstructorEvents.OnLargeIceClick)
                            }
                            OrderIcon(
                                icon = R.drawable.ice, state.ice == 3
                            ) {
                                viewModel.onEvent(CoffeeConstructorEvents.OnLargeIceClick)
                            }
                        }
                    }

                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 17.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.encyclopedia),
                    style = MainTheme.typography.headlineMedium,
                    color = green1
                )
                Spacer(Modifier.weight(1f))
                MyIcon(icon = R.drawable.up, tintColor = green1) {
                    viewModel.onEvent(CoffeeConstructorEvents.OnEncyclopediaClick)
                }
            }

            Spacer(Modifier.weight(1f))
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
    if (state.isMilkItems) {

        DownMenu(
            state.milkItems,
            stringResource(R.string.type_milk),
            state.milk,
            {
                viewModel.onEvent(CoffeeConstructorEvents.OnDismissMenuClick)

            }
        ) {
            viewModel.onEvent(CoffeeConstructorEvents.OnMilkItemClick(it))
        }
    }
    if (state.isSyrupItems) {

        DownMenu(
            state.syrupItems,
            stringResource(R.string.type_syrup),
            state.syrup,
            {
                viewModel.onEvent(CoffeeConstructorEvents.OnDismissMenuClick)
            }
        ) {
            viewModel.onEvent(CoffeeConstructorEvents.OnSyrupItemClick(it))

        }
    }
    if (state.desc != "") {
        EncyclopediaItem(state.desc)
    }
    MyDialog("Ошибка", state.errorMessage, state.isError) {
        viewModel.onEvent(CoffeeConstructorEvents.OnCloseDialog)
    }

}
