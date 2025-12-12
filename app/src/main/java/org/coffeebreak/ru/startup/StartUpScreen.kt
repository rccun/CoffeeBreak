package org.coffeebreak.ru.startup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun StartUpScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.startup),
            null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(0x371D2335))
        )
        Column(modifier = Modifier.align(Alignment.Center).fillMaxWidth().background(Color.Yellow)) {
            Image(
                painter = painterResource(R.drawable.cup),
                "cup",
                modifier = Modifier
                    .sizeIn(maxWidth = 98.dp, maxHeight = 98.dp)
                    .padding(bottom = 71.dp)
            )
//            Spacer(Modifier.weight(0.86f)) // 54

            Text(stringResource(R.string.splash), style = MainTheme.typography.displayLarge)
        }
    }
}