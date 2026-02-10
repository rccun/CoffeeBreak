package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.bgW
import org.coffeebreak.ru.theme.grayD8

@Composable
fun PointsCard(onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MainTheme.colorScheme.orderButton)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(25.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {
                Text(
                    stringResource(R.string.my_points),
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 14.sp,
                    color = grayD8
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    "240",
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 24.sp,
                    color = grayD8
                )
            }
            Spacer(Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0x19A2CDE9))
            ) {
                Text(
                    stringResource(R.string.pay_points),
                    modifier = Modifier
                        .padding(9.dp)
                        .clickable {
                            onClick()
                        },
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 10.sp,
                    color = grayD8
                )
            }
        }
        MyIcon(
            icon = R.drawable.beans,
            modifier = Modifier.align(Alignment.BottomEnd),
            tintColor = bgW.copy(alpha = 0.26f)
        )
    }
}