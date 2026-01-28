@file:OptIn(ExperimentalMaterial3Api::class)

package org.coffeebreak.ru.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.bgW

@Composable
fun PaymentComp(
    method: Int = 1,
    name: String,
    address: String,
    onDism: () -> Unit,
    onFirstClick: () -> Unit,
    onSecondClick: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDism() }
    ) {


//    Dialog(
//        { onDism() },
//        properties = DialogProperties(
//            usePlatformDefaultWidth = false
//        )
//    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 114.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(topEnd = 35.dp, topStart = 35.dp))
                    .background(MainTheme.colorScheme.bg)
                    .fillMaxWidth()
                    .padding(horizontal = 33.dp)

            ) {
                Text(
                    stringResource(R.string.pay_order),
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 20.sp,
                    color = MainTheme.colorScheme.icon,
                    modifier = Modifier.padding(top = 35.dp)
                )
                Spacer(Modifier.height(75.dp))
                Row {
                    Box(
                        modifier = Modifier
                            .size(47.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(MainTheme.colorScheme.myOrderBg),
                        contentAlignment = Alignment.Center
                    ) {
                        MyIcon(icon = R.drawable.cart, tintColor = MainTheme.colorScheme.titleText)
                    }
                    Spacer(Modifier.width(24.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text(
                            name,
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 12.sp,
                            color = MainTheme.colorScheme.titleText,
                        )
                        Text(
                            "Кофейня Coffee Break",
                            color = MainTheme.colorScheme.payAddress,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light,
                        )
                        Text(
                            address,
                            color = MainTheme.colorScheme.payAddress,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Light,
                        )
                    }
                }
                Spacer(Modifier.height(46.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MainTheme.colorScheme.myOrderBg)
                )
                {
                    Spacer(Modifier.width(21.dp))
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(
                                when (method) {
                                    0 -> {
                                        MainTheme.colorScheme.icon
                                    }

                                    1 -> {
                                        MainTheme.colorScheme.default
                                    }

                                    else -> {
                                        Color.Transparent
                                    }
                                }
                            )
                            .size(20.dp)
                            .clickable {
                                onFirstClick()
                            }
                    )
                    {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(18.dp)
                                .background(MainTheme.colorScheme.myOrderBg)
                                .align(Alignment.Center)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(10.dp)
                                    .align(Alignment.Center)
                                    .background(
                                        when (method) {
                                            0 -> {
                                                MainTheme.colorScheme.icon
                                            }

                                            else -> {
                                                MainTheme.colorScheme.myOrderBg
                                            }
                                        }
                                    )
                            )
                        }
                    }
                    Spacer(Modifier.width(21.dp))

                    Column {
                        Text(
                            "Оплата онлайн",
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 14.sp,
                            color = MainTheme.colorScheme.titleText
                        )
                        Spacer(Modifier.height(7.dp))
                        Text(
                            "СБП",
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 10.sp,
                            color = MainTheme.colorScheme.payMethod
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Image(
                        painter = painterResource(R.drawable.sbp),
                        "",
                        modifier = Modifier.padding(vertical = 18.dp)
                    )
                    Spacer(Modifier.width(15.dp))
                }
                Spacer(Modifier.height(19.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MainTheme.colorScheme.myOrderBg)
                )
                {
                    Spacer(Modifier.width(21.dp))
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(
                                when (method) {
                                    1 -> {
                                        MainTheme.colorScheme.icon
                                    }

                                    0 -> {
                                        MainTheme.colorScheme.default
                                    }

                                    else -> {
                                        Color.Transparent
                                    }
                                }
                            )
                            .size(20.dp)
                            .clickable {
                                onSecondClick()
                            }
                    )
                    {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(18.dp)
                                .background(MainTheme.colorScheme.myOrderBg)
                                .align(Alignment.Center)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(10.dp)
                                    .align(Alignment.Center)
                                    .background(
                                        when (method) {
                                            1 -> {
                                                MainTheme.colorScheme.icon
                                            }

                                            else -> {
                                                MainTheme.colorScheme.myOrderBg
                                            }
                                        }
                                    )
                            )
                        }
                    }
                    Spacer(Modifier.width(21.dp))

                    Column {
                        Text(
                            "Банковская карта",
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 14.sp,
                            color = MainTheme.colorScheme.titleText
                        )
                        Spacer(Modifier.height(7.dp))
                        Text(
                            "2540 xxxx xxxx 2648",
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 10.sp,
                            color = MainTheme.colorScheme.payMethod
                        )
                    }
                    Spacer(Modifier.weight(1f))
                    Image(
                        painter = painterResource(R.drawable.mir),
                        "",
                    )
                    Spacer(Modifier.width(7.dp))

                    Image(
                        painter = painterResource(R.drawable.union_pay),
                        "",
                        modifier = Modifier.padding(vertical = 25.dp)
                    )
                    Spacer(Modifier.width(15.dp))
                }

//                Spacer(Modifier.height(101.dp))
                Spacer(Modifier.weight(1f))

                Row {
                    Text(
                        stringResource(R.string.sum),
                        color = MainTheme.colorScheme.icon,
                        style = MainTheme.typography.chooseBarista,
                        fontSize = 12.sp
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        "300 ₽",
                        color = MainTheme.colorScheme.icon,
                        style = MainTheme.typography.chooseBarista,
                        fontSize = 13.sp
                    )
                }
                Spacer(Modifier.height(49.dp))
                Row() {

                    Box(modifier = Modifier.weight(1f)) {

                        Column(
                            horizontalAlignment = Alignment.End,
                            modifier = Modifier.align(Alignment.TopStart)
                        ) {
                            Text(
                                stringResource(R.string.total_coast),
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
                            .clickable {
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        MyIcon(icon = R.drawable.card, tintColor = bgW)
                        Text(
                            stringResource(R.string.pay_now),
                            style = MainTheme.typography.bodySmall,
                            fontSize = 14.sp,
                            color = bgW,
                            modifier = Modifier.padding(vertical = 14.dp)
                        )
                    }
                }
            }
        }
    }
}
