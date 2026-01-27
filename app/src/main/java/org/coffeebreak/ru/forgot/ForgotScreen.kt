package org.coffeebreak.ru.forgot

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.AuthTextField
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.login.LoginEvents
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun ForgotScreen(navController: NavController, viewModel: ForgotViewModel = hiltViewModel()) {
    val state = viewModel.state.value
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
                "Забыли пароль?",
                style = MainTheme.typography.bodyLarge,
                color = MainTheme.colorScheme.authLarge
            )
            Spacer(Modifier.height(24.dp))
            Text(
                "Введите адрес электронной почты", style = MainTheme.typography.titleMedium,
                color = MainTheme.colorScheme.authMedium
            )
            Spacer(Modifier.height(57.dp))
            AuthTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(ForgotEvents.OnEmailChange(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                placeholder = "Адрес электронной почты",
                icon = R.drawable.message
            )
            Spacer(Modifier.weight(1f))
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(ForgotEvents.OnNextClick)
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
            Spacer(Modifier.weight(2f))

        }
    }
}