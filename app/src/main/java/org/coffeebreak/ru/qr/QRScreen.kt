package org.coffeebreak.ru.qr

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.MyTopAppBar
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.bg
import org.coffeebreak.ru.theme.blue3

@Composable
fun QRScreen(navController: NavController, viewModel: QRViewModel = hiltViewModel()) {
    val qrColor = MainTheme.colorScheme.default.toArgb()
    val bgColor = MainTheme.colorScheme.bg.toArgb()
    LaunchedEffect(qrColor, bgColor) {
        viewModel.generate(qrColor, bgColor)
    }
    val array = viewModel.array.collectAsState().value
    val loading = viewModel.loading.collectAsState().value
    MyTopAppBar(
        text = stringResource(R.string.profile),
        isCart = false,
        onBackClick = { navController.popBackStack() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            modifier = Modifier
                .padding(horizontal = 40.dp)
        ) {
            Text(
                "Ваш персональный QR-код",
                style = MainTheme.typography.countryTitle,
                color = MainTheme.colorScheme.activeOrderPickup,
                fontSize = 20.sp
            )
            Spacer(Modifier.height(20.dp))
            if (!loading) {
                Image(
                    bitmap = array!!,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillWidth
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    "Покажите ваш QR-code для получения заказа",
                    style = MainTheme.typography.bodySmall,
                    fontSize = 18.sp,
                    color = blue3,
                    textAlign = TextAlign.Center
                )
            } else {
                Text("Generating")
            }
        }
    }
}