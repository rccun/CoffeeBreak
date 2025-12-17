package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.green1

@Composable
fun CafeItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(green1)
            .padding(15.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        MyIcon(icon = R.drawable.address, tintColor = Color.White)
        Spacer(Modifier.width(10.dp))
        Text(text, style = MainTheme.typography.titleLarge)
        Spacer(Modifier.weight(1f))
        MyIcon(icon = R.drawable.next2, tintColor = Color.White)
    }
}

//@Preview
//@Composable
//private fun Rewivew() {
//    CafeItem()

//}