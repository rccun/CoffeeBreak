package org.coffeebreak.ru.common

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.b1
import org.coffeebreak.ru.theme.lineColor
import java.util.Calendar


@Composable
fun RowItem(
    text: String,
    isLine: Boolean = true,
    align: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.padding(vertical = 13.dp),
        verticalAlignment = align
    ) {
        Text(
            text,
            color = MainTheme.colorScheme.orderText,
            style = MainTheme.typography.displaySmall,
            modifier = Modifier.widthIn(max = 170.dp)
        )
        Spacer(Modifier.weight(1f))
        content.invoke()
    }
    if (isLine) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(lineColor)
        )
    }

}

@Composable
fun BoxItem(isActive: Boolean, text: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .border(
                1.2.dp,
                color = if (isActive) {
                    b1
                } else {
                    MainTheme.colorScheme.unactiveBottomIcon
                },
                shape = RoundedCornerShape(50.dp)
            )
            .padding(vertical = 9.dp, horizontal = 20.dp)
            .clickable {
                onClick.invoke()
            }
    ) {
        Text(
            text,
            style = MainTheme.typography.titleSmall,
            color =
                if (isActive) { // выбрано
                    b1 // dark - b1, white/d8d8d8 white - b1, darkblue/d8d8d8
                } else { // не выбрано
                    MainTheme.colorScheme.unactiveRisColor
                },
        )
    }
}

@Composable
fun OrderIcon(icon: Int, isActive: Boolean, onClick: () -> Unit) {
    MyIcon(
        icon = icon, tintColor =
            if (!isActive) {
                MainTheme.colorScheme.unactiveBottomIcon
            } else {// dark = d8/b1 white = d8/black
                MainTheme.colorScheme.activeOrderPickup
            },
        onClick = {
            onClick()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderTimePicker(onTimeSelected: (Int, Int) -> Unit, isShow: Boolean = true) {

    val currentTime = Calendar.getInstance()
    currentTime.add(Calendar.MINUTE, 15)

    if (isShow) {
        TimePickerDialog(
            LocalContext.current,

            { _, hour, minute ->
                onTimeSelected(hour, minute)
            },
            currentTime.get(Calendar.HOUR_OF_DAY),
            currentTime.get(Calendar.MINUTE),
            true
        ).show()
    }
}