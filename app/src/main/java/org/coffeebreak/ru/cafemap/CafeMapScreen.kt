package org.coffeebreak.ru.cafemap

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.user_location.UserLocationObjectListener
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.image.ImageProvider
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.CafeItem
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3
import org.coffeebreak.ru.theme.green1

val points = listOf(
    Triple(51.767045, 55.115272, "ул. Туркестанская, 3"),
    Triple(51.771175, 55.124000, "ул. Чкалова, 32"),
    Triple(51.771000, 55.096000, "ул. Советская, 3")
)

@Composable
fun CafeMapScreen(navController: NavController) {
//    val adrss = listOf(
//        "ул. Туркестантская, 3",
//        "ул. Чкалова, 32",
//        "ул. Советская, 3",
//    )


    val context = LocalContext.current

    val mapView = remember { MapView(context) }

    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    fun moveHome() {
        val home = Point(51.765334, 75.124147)
        val placemark = mapView.map.mapObjects.addPlacemark(home)
        placemark.setIcon(ImageProvider.fromResource(context, R.drawable.home_point))
        placemark.isDraggable = false // если нужно, чтобы маркер не перетаскивался

        mapView.map.move(
            CameraPosition(
                home,
                13f,
                0f,
                0f
            ), Animation(Animation.Type.SMOOTH, 0.5f),
            null
        )
    }


    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            hasLocationPermission = granted
        }

    // 🔑 запрашиваем разрешение при первом входе
    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            permissionLauncher.launch(
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        }
    }
    DisposableEffect(Unit) {
        MapKitFactory.getInstance().onStart()
        mapView.onStart()

        onDispose {
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }
    LaunchedEffect(hasLocationPermission) {

        if (!hasLocationPermission) return@LaunchedEffect
        val mapKit = MapKitFactory.getInstance()
//        moveHome()
        val userLocationLayer =
            mapKit.createUserLocationLayer(mapView.mapWindow)

        userLocationLayer.isVisible = true

        // 4️⃣ Как только MapKit узнал координаты — двигаем камеру
        userLocationLayer.setObjectListener(
            object : UserLocationObjectListener {

                override fun onObjectAdded(view: UserLocationView) {
                    Log.d("MAP", "USER LOCATION ADDED")
                    val point = view.arrow.geometry

                    mapView.map.move(
                        CameraPosition(
                            point,
                            17f, // приближение
                            0f,
                            0f
                        )
                    )

                    // важно: срабатывает ОДИН раз
                    userLocationLayer.setObjectListener(null)
                }

                override fun onObjectRemoved(view: UserLocationView) {}
                override fun onObjectUpdated(
                    view: UserLocationView,
                    event: ObjectEvent
                ) {
                    Log.e("TAG", "onObjectUpdated: ")
                }
            }
        )
    }


    Box() {

        val mapHeight = remember { mutableStateOf(0.dp) }

        val density = LocalDensity.current
        Column(/*modifier = Modifier.fillMaxSize()*/) {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
//                .height(200.dp),
                , factory = {
                    mapView
                }
            )
            LaunchedEffect(Unit) {
                addCustomMarkers(mapView)
            }



            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .clip(
                        RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
                    )
                    .background(green1)
                    .onGloballyPositioned {
                        val height = it.size.height
                        mapHeight.value = with(density) { height.toDp() }
                        Log.e("TAG", "CafeMapScreen" + mapHeight.value.toString())
                    }
            ) {
                Text(
                    stringResource(R.string.choose_cafe),
                    style = MainTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 27.dp)
                )
                Box(
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
                            val addresses = points.map { it.third }

                            CafeItem(addresses[i], onClick = {
                                val point = points.find {
                                    it.third == addresses[i]
                                }!!

                                mapView.map.move(
                                    CameraPosition(
                                        Point(point.first, point.second),
                                        17.0f,
                                        0.0f,
                                        0.0f
                                    ),
                                    Animation(Animation.Type.SMOOTH, 0.5f),
                                    null
                                )
                                navController.navigate(Route.Menu)
                            })
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 30.dp)
                .fillMaxHeight()
        ) {
            Spacer(Modifier.weight(1f))
            FloatingActionButton(
                onClick = { moveHome() }, modifier = Modifier
                    .background(Color.Transparent)
                    .padding(bottom = 35.dp),
                containerColor = blue3,
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                ),
                shape = CircleShape
            ) {
                MyIcon(
                    icon = R.drawable.go_home,
                )
            }
            Spacer(Modifier.height(mapHeight.value))//444 318
        }
    }
}


fun addCustomMarkers(mapView: MapView) {
    // Список точек с координатами и названием

    val mapObjects = mapView.map.mapObjects

    points.forEach { (lat, lon, name) ->
        val point = Point(lat, lon)
        mapObjects.addPlacemark(point).apply {
            setIcon(
                ImageProvider.fromResource(
                    mapView.context,
                    R.drawable.point2
                )
            ) // кастомная иконка
//            setText(name) // подпись (только Lite)
        }
    }
}