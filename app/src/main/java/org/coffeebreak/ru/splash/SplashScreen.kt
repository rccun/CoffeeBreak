package org.coffeebreak.ru.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.lightAlphaGray
import org.coffeebreak.ru.theme.lightGray
import org.coffeebreak.ru.theme.lightGreen
import org.coffeebreak.ru.theme.textColor

@Composable
fun SplashScreen(navController: NavController, viewModel: SplashViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    LaunchedEffect(state.isTimeOut) {
        if (state.isTimeOut) {
            navController.navigate(Route.Login)
        }
    }
    val page = remember { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(60.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(lightGreen),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.weight(1f)) // 63
            Image(
                painter = painterResource(R.drawable.cup),
                "cup",
                modifier = Modifier.sizeIn(maxWidth = 98.dp, maxHeight = 98.dp)
            )
            Spacer(Modifier.weight(0.86f)) // 54

            Text(stringResource(R.string.splash), style = MainTheme.typography.displayLarge)

            Spacer(Modifier.weight(0.73f)) // 46
        }
        Text(
            stringResource(R.string.splash_title),
            style = MainTheme.typography.displayMedium,
            fontSize = 28.sp,
            color = textColor,
            modifier = Modifier.padding(top = 25.dp, start = 68.dp, end = 68.dp),
            textAlign = TextAlign.Center
        )
        Text(
            stringResource(R.string.splash_desc),
            style = MainTheme.typography.displayMedium,
            fontSize = 18.sp,
            color = lightGray
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(top = 43.dp)
            )
        {
            repeat(3) { i ->
                if (i == page.value) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .height(10.dp)
                            .width(33.dp)
                            .background(lightGreen)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(10.dp)
                            .background(
                                lightAlphaGray
                            )
                    )
                }
            }
        }
    }
}