package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.blue3
import org.coffeebreak.ru.theme.lightGray

@Composable
fun LoyaltyCard(rate: Int) {
    Column(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .background(blue3)) {
        Column(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.padding(horizontal = 30.dp, vertical = 14.dp)) {
                Text(stringResource(R.string.loalty))
                Spacer(Modifier.weight(1f))
                Text("4 / 6")


            }
            Spacer(Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    repeat(6) { i ->
                        MyIcon(
                            icon = R.drawable.cup_l, tintColor = if (i > rate) {
                                lightGray
                            } else {
                                Color.Unspecified
                            }
                        ) {

                        }// 89 49

                    }
                }
                Text("16%")
            }

        }

        Spacer(Modifier.weight(0.55f))
    }
}

@Preview(showBackground = true)
@Composable
private fun S() {
    LoyaltyCard(3)
}