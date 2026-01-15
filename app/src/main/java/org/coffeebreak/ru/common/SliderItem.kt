package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.coffeebreak.ru.theme.MainTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderItem(value: Float, onValueChange: (Float) -> Unit, modifier: Modifier = Modifier) {
    Slider(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        valueRange = 0f..1f,
        thumb = {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(28.dp)
                    .shadow(
                        6.dp,
                        CircleShape,
                        ambientColor = Color.Black.copy(alpha = 0.12f)
                    )
                    .background(Color.White)

            )
        },

        track = { sliderState ->
            SliderDefaults.Track(
                sliderState = sliderState,
                modifier = Modifier.height(4.dp),
                colors = SliderDefaults.colors(
                    activeTrackColor = MainTheme.colorScheme.sliderTrack,
                    inactiveTrackColor = Color(0x20787880),

                    )

            )
        },
        modifier = modifier.padding(0.dp)
//        modifier = Modifier.height(4.dp)

    )
}