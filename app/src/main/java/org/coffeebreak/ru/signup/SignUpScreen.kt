package org.coffeebreak.ru.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3
import org.coffeebreak.ru.theme.lightGreen

@Composable
fun SignUpScreen(navController: NavController, viewModel: SignUpViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    Column {

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.back), "back",
            modifier = Modifier
                .clickable { navController.navigate(Route.Splash) }
                .padding(start = 25.dp, top = 25.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 40.dp)
        ) {
            Spacer(Modifier.weight(1f)) // 51

            Text(
                stringResource(R.string.register),
                style = MainTheme.typography.bodyLarge,
            )
            Spacer(Modifier.height(24.dp))
            Text(stringResource(R.string.create_acc), style = MainTheme.typography.titleMedium)
            Spacer(Modifier.height(57.dp))
            Column(verticalArrangement = Arrangement.spacedBy(36.dp)) {


                AuthTextField(
                    value = state.name,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvents.OnNameChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    placeholder = "Имя пользователя",
                    icon = R.drawable.name
                )
                AuthTextField(
                    value = state.phone,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvents.OnPhoneChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    placeholder = "Номер мобильного телефона",
                    icon = R.drawable.phone
                )
                AuthTextField(
                    value = state.email,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvents.OnEmailChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    placeholder = "Адрес электронной почты",
                    icon = R.drawable.message
                )
                AuthTextField(
                    value = state.password,
                    onValueChange = {
                        viewModel.onEvent(SignUpEvents.OnPasswordChange(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(30.dp),
                    placeholder = "Пароль",
                    icon = R.drawable.lock,
                    isTrailingIcon = true,
                    onShowClick = {viewModel.onEvent(SignUpEvents.OnShowClick)},
                    isShow = state.isShow
                )
            }
            Text(
                stringResource(R.string.terms),
                style = MainTheme.typography.titleMedium,
                color = blue3,
                modifier = Modifier
                    .padding(top = 27.dp)
            )
            FloatingActionButton(
                onClick = { navController.navigate(Route.Main) },
                modifier = Modifier
                    .background(Color.Transparent)
                    .align(Alignment.End)
                    .padding(top = 23.dp),
                containerColor = lightGreen,
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
            Spacer(Modifier.weight(3.53f))

            Text(
                stringResource(R.string.already_login),
                style = MainTheme.typography.titleMedium,
                color = blue3,
                modifier = Modifier
                    .clickable{ navController.navigate(Route.Login) }
            )

            Spacer(Modifier.weight(1.6f))
        }
    }
} // 180 82