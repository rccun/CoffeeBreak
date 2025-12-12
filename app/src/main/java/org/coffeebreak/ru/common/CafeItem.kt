package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.lightGreen

@Composable
fun CafeItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(lightGreen)
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = ImageVector.vectorResource(R.drawable.address), null, tint = Color.White)
        Spacer(Modifier.width(10.dp))
        Text(text, style = MainTheme.typography.titleLarge)
        Spacer(Modifier.weight(1f))
        Icon(imageVector = ImageVector.vectorResource(R.drawable.next2), null, tint = Color.White)
    }
}

//@Preview
//@Composable
//private fun Rewivew() {
//    CafeItem()

//}