package org.coffeebreak.ru.common

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.theme.MainTheme

data class BottomIcon(
    val route: Route,
    @field:DrawableRes val icon: Int,
)

@Composable
fun BottomNav(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController(), //isSelected: Boolean = false,
    currentRoute: String? = null
) {
    val icons = listOf(
        BottomIcon(Route.Menu, R.drawable.menu),
        BottomIcon(Route.Gift, R.drawable.gift),
        BottomIcon(Route.CreateOrder("null"), R.drawable.order),
    )
    val routeName = currentRoute
        ?.substringBefore("/")   // order/{imageUrl}
        ?.substringBefore("?")   // order?imageUrl=xxx
        ?.lowercase()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 25.dp, end = 25.dp, bottom = 22.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(MainTheme.colorScheme.bottomNav)
                .padding(vertical = 21.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            icons.forEach { i ->
                Log.e("piska", "piska: $currentRoute", );
                Log.e("siska", "piska: ${i.route}", );
                val itemRouteName = i.route::class.simpleName!!.lowercase()

                MyIcon(
                    icon = i.icon,
                    tintColor = if (itemRouteName == routeName) {
                        MainTheme.colorScheme.activeBottomIcon
                    } else {
                        MainTheme.colorScheme.unactiveBottomIcon
                    },
                    onClick = {
                        if (routeName != itemRouteName) {
                            navController.navigate(i.route)
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun L() {
//    BottomNav()
}