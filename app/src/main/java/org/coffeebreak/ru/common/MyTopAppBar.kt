package org.coffeebreak.ru.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun MyTopAppBar(
    text: String,
    isBack: Boolean = true,
    onBackClick: () -> Unit = {},
    isCart: Boolean = true,
    onCartClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (isBack) {
                MyIcon(
                    icon = R.drawable.back,
                    tintColor = MainTheme.colorScheme.icon
                ) {
                    onBackClick()
                }
            }

            Text(
                text,
                color = MainTheme.colorScheme.titleText,
                style = MainTheme.typography.labelMedium,
                fontSize = 16.sp
            )
            Box() {
                if (isCart) {
                    MyIcon(
                        icon = R.drawable.cart, tintColor = /*if (isCart) {*/
                            MainTheme.colorScheme.icon
//                        } else {
//                            MainTheme.colorScheme.bg
//                        }
                    ) {
                        onCartClick()
                    }
                }
            }
        }

        content()
    }
}