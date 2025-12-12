package org.coffeebreak.ru.cafemap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.CafeItem
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.lightGreen

@Composable
fun CafeMapScreen(navController: NavController) {
    val adrss = listOf(
        "ул. Туркестантская, 3",
        "ул. Чкалова, 32",
        "ул. Советская, 3",
    )


    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    DisposableEffect(Unit) {
        MapKitFactory.getInstance().onStart()
        mapView.onStart()

        onDispose {
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            factory = { mapView }
        )


        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .clip(
                    RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                )
                .background(lightGreen)
        ) {
            Text(
                stringResource(R.string.choose_cafe),
                style = MainTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 27.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                    )
                    .background(Color.White)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(7.dp),
                    modifier = Modifier.padding(vertical = 21.dp, horizontal = 30.dp)
                ) {
                    repeat(3) { i ->
                        CafeItem(adrss[i])
                    }
                }
            }
        }
    }
}