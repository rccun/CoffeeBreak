package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3
import org.coffeebreak.ru.theme.font
import org.coffeebreak.ru.theme.lineColor

@Composable
fun PointsRow(coffeeTitle: String, date: String, time: String) {
    Column {
        Spacer(Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(
                    coffeeTitle,
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 12.sp,
                    color = blue3
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    "$date | $time",
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 10.sp,
                    color = MainTheme.colorScheme.timeCartItem
                )
            }
            Spacer(Modifier.weight(1f))
            Text("+ 12 баллов", style = MainTheme.typography.chooseBarista, color = blue3)
        }
        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(lineColor)
        )
    }
}