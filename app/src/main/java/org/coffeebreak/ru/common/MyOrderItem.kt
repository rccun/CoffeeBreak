package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun MyOrderItem(imageUrl: String, coffeeTitle: String, properties: String, count: Int, coast: Int) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(MainTheme.colorScheme.myOrderBg)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) { // bg grayWhite
        MyAsyncImage(
            modifier = Modifier
                .padding(20.dp)
                .heightIn(max = 51.dp),
            imageUrl = imageUrl,
            isMaxWidth = false,
            contentScale = ContentScale.FillHeight
        )

        Column() {
            Text(
                coffeeTitle,
                style = MainTheme.typography.chooseBarista,
                fontSize = 12.sp,
                color = MainTheme.colorScheme.titleText
            )//b2 db4
            Text(
                properties,
                color = MainTheme.colorScheme.myOrderProperties,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(bottom = 7.dp, top = 5.dp)
            )
            Text(
                "x $count", color = MainTheme.colorScheme.myOrderCount,
                style = MainTheme.typography.bodySmall,
                fontSize = 12.sp,
            )
        }
        Spacer(Modifier.weight(1f))
        Text(
            "$coast ₽", style = MainTheme.typography.bodySmall,
            color = MainTheme.colorScheme.titleText
        )
        Spacer(Modifier.width(14.dp))
    }
}