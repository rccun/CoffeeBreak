package org.coffeebreak.ru.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
//import io.github.jan.supabase.compose.auth.composable.GoogleDialogType
//import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
//import io.github.jan.supabase.compose.auth.composeAuth
import kotlinx.coroutines.delay
import org.coffeebreak.data.data_source.InitSupabaseClient
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.AuthTextField
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3
import org.coffeebreak.ru.theme.lightGray

data class MyIcon(
    val icon: Int,
    val onClick: () -> Unit
)

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {
    val state = viewModel.state.value
//    val t = InitSupabaseClient.client.composeAuth.rememberSignInWithGoogle(
//        type = GoogleDialogType.BOTTOM_SHEET,
//        onResult = {
//            Log.e("TAG", "LoginScreen: $it")
//        }
//    ) {
//        Log.e("TAG", "LoginScreen: Fallback")
//    }

    val a = remember { mutableStateOf(false) }
    LaunchedEffect(a.value) {
        if (a.value) {
            delay(1000)
//            t.startFlow()
        }
    }

    val icons = listOf(
        MyIcon(R.drawable.yandex, {}),
        MyIcon(R.drawable.google, { a.value = !a.value }),
        MyIcon(R.drawable.vk) {},
    )

    Column {

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
                stringResource(R.string.login),
                style = MainTheme.typography.bodyLarge,
                color = MainTheme.colorScheme.authLarge
            )
            Spacer(Modifier.height(24.dp))
            Text(stringResource(R.string.welcome), style = MainTheme.typography.titleMedium,
                color = MainTheme.colorScheme.authMedium
                )
            Spacer(Modifier.height(57.dp))
//        Box() {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(IntrinsicSize.Max)/*.background(Color.Red)*/.align(Alignment.TopStart)
//            ) {
//                LeadingIcon()
            AuthTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(LoginEvents.OnEmailChange(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                placeholder = "Адрес электронной почты",
                icon = R.drawable.message
            )
            Spacer(Modifier.height(36.dp))
            AuthTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(LoginEvents.OnPasswordChange(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                placeholder = "Пароль",
                icon = R.drawable.lock,
                isTrailingIcon = true,
                onShowClick = {viewModel.onEvent(LoginEvents.OnShowClick)},
                isShow = state.isShow
            )
//            TextField()
//            }
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(1.dp)
//                    .background(textFieldColor)
//                    .align(Alignment.BottomStart)
//            )
//        }
            Text(
                stringResource(R.string.forgot),
                style = MainTheme.typography.titleMedium,
                color = MainTheme.colorScheme.authForget,
                modifier = Modifier
                    .padding(top = 27.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(Modifier.weight(4.33f)) // 136
            FloatingActionButton(
                onClick = { navController.navigate(Route.Main) },
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
            Column(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                var width by remember { mutableStateOf(0.dp) }
                val density = LocalDensity.current
                Text(
                    stringResource(R.string.enter_with),
                    style = MainTheme.typography.titleMedium,
                    color = MainTheme.colorScheme.default,
                    modifier = Modifier.onGloballyPositioned {
                        val height = it.size.width
                        width = with(density) { height.toDp() } + 19.dp
                    }
                )
                Spacer(Modifier.height(14.dp))
                Row(
                    modifier = Modifier.width(width),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    repeat(icons.size) { t ->
                        val i = icons[t]
                        Image(
                            painter = painterResource(i.icon),
                            null,
                            modifier = Modifier
                                .size((width - 10.dp * 2) / 3)
                                .clickable { i.onClick })
                    }
                }
            }
            Row() {
                Text(
                    stringResource(R.string.first),
                    style = MainTheme.typography.titleMedium,
                    color = lightGray,
                )
                Text(
                    stringResource(R.string.register),
                    style = MainTheme.typography.titleMedium,
                    color = blue3,
                    modifier = Modifier.clickable { navController.navigate(Route.SignUp) }
                )
            }
            Spacer(Modifier.weight(1.6f)) // 82
        }
    }
}