package org.coffeebreak.ru.common

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3
import org.coffeebreak.ru.theme.lineColor

@Composable
fun CartItem(
    imageUrl: String,
    coffeeTitle: String,
    address: String,
    date: String,
    createdAt: String,
    time: String,
    coast: Int,
    isButton: Boolean = false
) {
    Column {

        Spacer(Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box() {

                MyAsyncImage(
                    modifier = Modifier
                        .heightIn(max = 44.dp),
                    imageUrl = imageUrl,
                    isMaxWidth = false,
                    contentScale = ContentScale.FillHeight
                )
            }
            Spacer(Modifier.width(18.dp))
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    coffeeTitle,
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 14.sp,
                    color = blue3
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MyIcon(
                        icon = R.drawable.location_icon,
                        tintColor = MainTheme.colorScheme.locationCartItem
                    ) // b3 blue3 80
                    Spacer(Modifier.width(6.dp))
                    Text(
                        address,
                        style = MainTheme.typography.chooseBarista,
                        fontSize = 10.sp, color = MainTheme.colorScheme.authMedium
                    ) // b3 blue3
                }
                Text(
                    "$date | $createdAt | к ${time.dropLast(3)}",
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 10.sp,
                    color = MainTheme.colorScheme.timeCartItem
                ) // b3 blue3 22
            }
            Spacer(Modifier.weight(1f))
            if (isButton) {

                Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                    Text(
                        "$coast P", color = blue3,
                        style = MainTheme.typography.chooseBarista,
                    )
                    Spacer(Modifier.height(7.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(blue3)
                    ) {
                        Text(
                            stringResource(R.string.buy),
                            style = MainTheme.typography.chooseBarista,
                            fontSize = 10.sp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 15.dp)
                        ) // 324A59
                    }
                }
            } else {
                Text(
                    "$coast P", color = blue3,
                    style = MainTheme.typography.chooseBarista,
                )
            }
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