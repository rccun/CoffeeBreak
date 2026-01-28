package org.coffeebreak.ru.two_factor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun TwoFactorScreen(navController: NavController, viewModel: TwoFactorViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            navController.navigate(Route.Reset)
        }
    }
    Column() {
        MyIcon(
            icon = R.drawable.back,
            modifier = Modifier
                .clickable { navController.navigate(Route.Splash) }
                .padding(start = 25.dp, top = 25.dp),
            tintColor = MainTheme.colorScheme.authLarge
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)
        ) {
            Spacer(Modifier.weight(1f)) // 51

            Text(
                "Проверка",
                style = MainTheme.typography.bodyLarge,
                color = MainTheme.colorScheme.authLarge
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "Введите код, который мы вам отправили на почту",
                style = MainTheme.typography.titleMedium,
                color = MainTheme.colorScheme.authMedium
            )
            Spacer(Modifier.height(57.dp))
            BasicTextField(
                value = state.otp,
                { i ->
                    if (i.length <= 8 && i.all { it.isDigit() }) {
                        viewModel.onEvent(TwoFactorEvents.OnDigitEntered(i))

                        if (i.length == 8) {
                            viewModel.onEvent(TwoFactorEvents.OnEnterEnded)
                        }
                    }

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                cursorBrush = SolidColor(Color.Transparent),
                textStyle = TextStyle(
                    color = Color.Transparent,
                    fontSize = 1.sp
                ),
                modifier = Modifier
                    .focusRequester(focusRequester)
//                    .onFocusChanged { state ->
//                        if (state.isFocused) {
//                            keyboardController?.show()
//                        }
//                    }
                    .fillMaxWidth(),
                decorationBox = { itf ->
                    Row(
                        modifier = Modifier
                            .clickable {
                                focusRequester.requestFocus()
                                keyboardController?.show()
                            },
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        repeat(8) { i ->
                            val char = state.otp.getOrNull(i)?.toString() ?: ""
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(61.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(MainTheme.colorScheme.authOtp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = char,
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    itf()
                }
            )


            Spacer(Modifier.weight(1.34f)) // 51

            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(TwoFactorEvents.OnEnterEnded)
//                    navController.navigate(Route.Menu)
                },
                modifier = Modifier
                    .background(Color.Transparent)
                    .align(Alignment.End)
                    .padding(bottom = 21.dp),
                containerColor = MainTheme.colorScheme.green,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                ),
                shape = CircleShape
            ) {
                Icon(imageVector = ImageVector.vectorResource(R.drawable.next), null)
            }
            Spacer(Modifier.weight(6.9f))
        }
    }
}