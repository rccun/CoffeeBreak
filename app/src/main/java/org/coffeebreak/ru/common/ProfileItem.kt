package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun ProfileItem(icon: Int, title: String, text: String, isDefault: Boolean = true, onClick: () -> Unit = {}) {
    Column() {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MainTheme.colorScheme.circleBox)
            ) {
                MyIcon(
                    icon = icon,
                    tintColor = MainTheme.colorScheme.authTerms,
                    modifier = Modifier
                        .padding(13.dp)
                        .align(Alignment.Center)
                )

            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    title,
                    style = MainTheme.typography.chooseBarista,
                    fontSize = 10.sp,
                    color = MainTheme.colorScheme.profileText
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text,
                    style = MainTheme.typography.titleLarge,
                    color = MainTheme.colorScheme.profileText2
                ) // blue3 aaaaaa
            }
            Spacer(Modifier.weight(1f))
            MyIcon(
                icon = if (isDefault) {
                    R.drawable.edit
                } else {
                    R.drawable.next2
                }, tintColor = MainTheme.colorScheme.authTerms
            ) {
                onClick()
            }
        }
        Spacer(Modifier.height(26.dp))
    }
}
