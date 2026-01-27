package org.coffeebreak.ru.my_order

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.common.MyOrderItem
import org.coffeebreak.ru.common.PaymentComp
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.bgW
import org.coffeebreak.ru.theme.red
import kotlin.math.roundToInt

@Composable
fun MyOrderScreen(navController: NavController, viewModel: MyOrderViewModel = hiltViewModel()) {
    val isPayment = remember { mutableStateOf(true) }

    val state = viewModel.state.value
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Spacer(Modifier.height(25.dp))
        MyIcon(icon = R.drawable.back, tintColor = MainTheme.colorScheme.icon) {
            navController.popBackStack()
        }
        Text(
            stringResource(R.string.my_order),
            modifier = Modifier.padding(vertical = 24.dp),
            style = MainTheme.typography.chooseBarista,
            fontSize = 20.sp,
            color = MainTheme.colorScheme.titleText
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(
                10
            ) { item ->
                SwipeToReveal(
                    onDelete = { }
                ) {
                    MyOrderItem(
                        "https://wsmwattyuklbdszczfpz.supabase.co/storage/v1/object/public/coffees/americano.png",
                        "Американо",
                        "sdfksflsd",
                        1,
                        100
                    )

                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Row(modifier = Modifier.padding(horizontal = 10.dp)) {

            Box(modifier = Modifier.weight(1f)) {

                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        stringResource(R.string.sum),
                        color = MainTheme.colorScheme.profileText,
                        style = MainTheme.typography.chooseBarista,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.height(3.dp))
                    Text(
                        "300 ₽",
                        style = MainTheme.typography.bodySmall,
                        fontSize = 24.sp,
                        color = MainTheme.colorScheme.unactiveRisColor
                    )

                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                    .clip(
                        RoundedCornerShape(30.dp)
                    )
                    .background(MainTheme.colorScheme.orderButton)
                    .weight(1f)
                    .clickable{
                        isPayment.value = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                MyIcon(icon = R.drawable.cart, tintColor = bgW)
                Text(
                    stringResource(R.string.next),
                    style = MainTheme.typography.bodySmall,
                    fontSize = 14.sp,
                    color = bgW,
                    modifier = Modifier.padding(vertical = 14.dp)
                )
            }

        }
    }

    if (isPayment.value) {
        PaymentComp(name = "имя", address = "адрес" , onDism = {
            isPayment.value = false
        }, onFirstClick =  {
            viewModel.onEvent(MyOrderEvents.OnPaymentChange(0))
        }, onSecondClick = {
            viewModel.onEvent(MyOrderEvents.OnPaymentChange(1))

        },
            method = state.payment)
    }
}

enum class SwipeState {
    Closed,
    Open
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeToReveal(
    modifier: Modifier = Modifier,
    revealWidth: Dp = 59.dp,
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    val density = LocalDensity.current
    val revealPx = with(density) { revealWidth.toPx() }

    val state = remember {
        AnchoredDraggableState(
            initialValue = SwipeState.Closed,
            anchors = DraggableAnchors {
                SwipeState.Closed at 0f
                SwipeState.Open at -revealPx
            },
            confirmValueChange = { true }
        )
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {

//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(end = 16.dp)
//                .background(Color.Green),
//            contentAlignment = Alignment.CenterEnd
//        ) {
//            MyIcon(icon = R.drawable.delete, tintColor = red) {
//
//            }
//        }
        Box(
            modifier = Modifier
                .matchParentSize(),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(48.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(MainTheme.colorScheme.deleteComp),
                contentAlignment = Alignment.Center
            ) {
                MyIcon(
                    icon = R.drawable.delete,
                    tintColor = red
                )
            }
        }

        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = state.requireOffset().roundToInt(),
                        y = 0
                    )
                }
                .anchoredDraggable(
                    state = state,
                    orientation = Orientation.Horizontal
                )
                .fillMaxWidth()
        ) {
            content()
        }
    }
}
