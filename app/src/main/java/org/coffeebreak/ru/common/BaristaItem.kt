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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.green3
import org.coffeebreak.ru.theme.red

@Composable
fun BaristaItem(
    avatarUrl: String,
    name: String,
    skill: String,
    status: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(MainTheme.colorScheme.baristaItem)
            .clickable {

                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        MyAsyncImage(
            imageUrl = avatarUrl,
            modifier = Modifier
                .padding(9.dp)
                .size(62.dp)
                .clip(RoundedCornerShape(16.dp)),
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                name,
                style = MainTheme.typography.displayMedium,
                color = MainTheme.colorScheme.default,
                fontSize = 15.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                skill,
                color = MainTheme.colorScheme.baristaSkill,
                style = MainTheme.typography.displayMedium,
                fontSize = 14.sp
            )

        }
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(15.dp)
                .background(
                    if (status) {
                        green3
                    } else {
                        red
                    }
                )
        )
        Spacer(Modifier.width(34.dp))
    }
}
