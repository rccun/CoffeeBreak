package org.coffeebreak.ru.common

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Composable
fun MyIcon(modifier: Modifier = Modifier, icon: Int, tintColor: Color? = null) {
    Icon(
        imageVector = ImageVector.vectorResource(icon),
        null,
        tint = tintColor ?: LocalContentColor.current,
        modifier = modifier
    )
}