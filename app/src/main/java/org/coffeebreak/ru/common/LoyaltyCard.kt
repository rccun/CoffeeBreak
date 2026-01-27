package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
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
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3
import org.coffeebreak.ru.theme.grayD8
import org.coffeebreak.ru.theme.green1
import org.coffeebreak.ru.theme.lightGray

@Composable
fun LoyaltyCard(rate: Int) {
    Column(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(blue3)
        .heightIn(max = 138.dp)) {
        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.padding(horizontal = 30.dp, vertical = 14.dp)) {
                Text(stringResource(R.string.loalty),
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 14.sp,
                    color = grayD8)
                Spacer(Modifier.weight(1f))
                Text("4 / 6",
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 14.sp,
                    color = grayD8
                )
            }
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    repeat(6) { i ->
                        MyIcon(
                            icon = R.drawable.cup_l, tintColor = if (i + 1 > rate) {
                                lightGray
                            } else {
                                Color.Unspecified
                            }
                        ) {

                        }// 89 49

                    }
                }
                Text("16%",
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 24.sp,
                    color = green1
                )
            }

        }

        Spacer(Modifier.weight(0.55f))
    }
}