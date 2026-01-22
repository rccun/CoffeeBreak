package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun FeedBackOrder(rate: Int, onDism: () -> Unit, onClick: () -> Unit, onStarClick: (Int) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(Modifier.weight(1f)) // 38 300 38
        Dialog(onDismissRequest = {onDism()}) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(19.dp))
                    .background(MainTheme.colorScheme.baristaItem)
                    .weight(7.89f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {// bg white
                Spacer(Modifier.height(18.dp))
                Text(
                    "Заказ выполнен.",
                    style = MainTheme.typography.bodySmall,
                    color = MainTheme.colorScheme.rateOrderText,
                    fontSize = 18.sp, // b2 black,
                )
                Text(
                    "Пожалуйста, оцените сервис.",
                    style = MainTheme.typography.displayMedium,
                    color = MainTheme.colorScheme.orderPlacedTime
                ) // b3 black
                Spacer(Modifier.height(18.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFCFCFCF))
                )
                Row(
                    modifier = Modifier.padding(/*horizontal = 17.dp, */vertical = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(23.dp)
                ) {
                    repeat(5) { i -> // 0..4
                        MyIcon(
                            icon = R.drawable.star, tintColor = if (i <= rate) {
                                Color(0xFFFF9500)
                            } else {
                                Color(0xFFC7C7CC)
                            }
                        ) { onStarClick(i) }
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFCFCFCF))
                )
                Text(
                    "Оценить",
                    style = MainTheme.typography.displayMedium,
                    color = MainTheme.colorScheme.rateOrderText,
                    modifier = Modifier.padding(vertical = 18.dp).clickable{
                        onClick()
                        onDism()
                    }
                ) // b2 black
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color(0xFFCFCFCF))
                )
                Text(
                    "Нет, спасибо",
                    style = MainTheme.typography.displayMedium,
                    color = MainTheme.colorScheme.rateOrderText,
                    modifier = Modifier.padding(vertical = 18.dp).clickable{onDism()}

                )
            }
        }
        Spacer(Modifier.weight(1f))
    }
}