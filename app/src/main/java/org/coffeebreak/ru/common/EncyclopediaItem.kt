package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.bgW
import org.coffeebreak.ru.theme.green1

@Composable
fun EncyclopediaItem(text: String, onClick: ()  -> Unit) {
    Column {
        Spacer(Modifier.weight(1f))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp))
                .background(
                    green1
                )
                .fillMaxWidth()
                .padding(horizontal = 35.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.encyclopedia),
                style = MainTheme.typography.bodyLarge,
                fontSize = 20.sp,
                color = bgW,
            )
            Text(
                text, style = MainTheme.typography.titleMedium, fontSize = 16.sp, color = bgW,
                modifier = Modifier.padding(top = 30.dp, bottom = 37.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 23.dp)
            ) {
                Text(
                    stringResource(R.string.skip),
                    style = MainTheme.typography.displaySmall,
                    fontSize = 18.sp,
                    color = bgW,
                    modifier = Modifier.clickable {
                        onClick()
                    }
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    repeat(3) { i ->
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(Color.White)
                                .size(8.dp)
                        )
                    }
                }
                Text(
                    stringResource(R.string.next),
                    style = MainTheme.typography.displaySmall,
                    fontSize = 18.sp,
                    color = bgW,
                    modifier = Modifier.clickable {
                        onClick()
                    }
                )
            }
        }
    }
}