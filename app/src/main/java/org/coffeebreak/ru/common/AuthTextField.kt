package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    icon: Int,
    placeholder: String = "",
    isTrailingIcon: Boolean = false,
    isShow: Boolean = true,
    onShowClick: () -> Unit = {}
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (isShow) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        decorationBox = {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    LeadingIcon(icon)
                    Spacer(Modifier.width(20.dp))

                    if (value.isBlank()) {
                        Text(placeholder, style = MainTheme.typography.bodyMedium, color = MainTheme.colorScheme.authHint)
                    } else {
                        it.invoke()
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MainTheme.colorScheme.authTextField)
                )
                if (isTrailingIcon) {
                    MyIcon(
                        icon = R.drawable.show,
                        tintColor = Color(0xFF001833),
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 7.dp)
                            .clickable(onClick = onShowClick)

                    )
                }
            }
        },
        modifier = modifier,
        textStyle = MainTheme.typography.bodyMedium.copy(color = Color.Black)
    )
}