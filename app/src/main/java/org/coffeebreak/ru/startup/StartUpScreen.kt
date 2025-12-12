package org.coffeebreak.ru.startup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun StartUpScreen(navController: NavController, viewModel: StartUpViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(state.isTimeOut) {
        if (state.isTimeOut) {
            navController.navigate(Route.Cafe)
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.startup),
            null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x371D2335))
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Spacer(Modifier.weight(1.4f))
                Image(
                    painter = painterResource(R.drawable.cup),
                    "cup",
                    modifier = Modifier
//                    .sizeIn(maxWidth = 98.dp, maxHeight = 98.dp)
                        .weight(1f)
                        .padding(bottom = 71.dp),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(Modifier.weight(1.4f))
            }
//            Spacer(Modifier.weight(0.86f)) // 54

            Text(stringResource(R.string.splash), style = MainTheme.typography.displayLarge)
        }
    }
}