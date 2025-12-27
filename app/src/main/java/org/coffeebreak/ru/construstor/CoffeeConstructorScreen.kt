package org.coffeebreak.ru.construstor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.common.RowItem
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.grayD8

@Composable
fun CoffeeConstructorScreen(
    navController: NavController,
    viewModel: CoffeeConstructorViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
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
            MyIcon(icon = R.drawable.back, tintColor = MainTheme.colorScheme.icon)
            Text(
                stringResource(R.string.constructor),
                color = MainTheme.colorScheme.titleText,
                style = MainTheme.typography.labelMedium,
                fontSize = 16.sp
            )
            MyIcon(icon = R.drawable.cart, tintColor = MainTheme.colorScheme.icon)
        }
        RowItem(stringResource(R.string.choose_barista)) {
            MyIcon(icon = R.drawable.next2, tintColor = Color(0xFF7B6F72))
        }
        RowItem(stringResource(R.string.type_coffee)) {
            Column(modifier = Modifier
                .padding(start = 15.dp)) {
                SliderFun(
                    state.weight, {
                        viewModel.onEvent(CoffeeConstructorEvents.OnSliderChange(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(10.dp))
                Row() {
                    Text(
                        stringResource(R.string.arabic),
                        style = MainTheme.typography.displaySmall,
                        color = grayD8
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        stringResource(R.string.robusta),
                        style = MainTheme.typography.displaySmall,
                        color = grayD8
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderFun(value: Float, onValueChange: (Float) -> Unit, modifier: Modifier = Modifier) {
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
                    .background(Color.White)
            )
        },

        track = { sliderState ->
            SliderDefaults.Track(
                sliderState = sliderState,
                modifier = Modifier.height(4.dp),
                colors = SliderDefaults.colors(
                    activeTrackColor = MainTheme.colorScheme.sliderTrack,
                    inactiveTrackColor = Color(0x20787880)

                )

            )
        },
        modifier = modifier.padding(0.dp)
//        modifier = Modifier.height(4.dp)

    )
}
//@Preview
//@Composable
//private fun s(value: Float, onValueChange: () -> Unit) {
//    SliderFun() { }
//}