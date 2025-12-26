package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun LeadingIcon(icon: Int) {

    var iconHeight by remember { mutableStateOf(0.dp) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 3.5.dp)
    ) {
        MyIcon(
            modifier = Modifier
                .onGloballyPositioned {
                    val height = it.size.height.dp
                    iconHeight = height
                }
                .padding(end = 10.dp, start = 10.dp),
            tintColor = MainTheme.colorScheme.authIcon,
            icon = icon
        )

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(iconHeight)
                .background(MainTheme.colorScheme.authTextField)
        )
    }
}

//@Preview
//@Composable
//private fun LIprew() {
//    LeadingIcon()
//}