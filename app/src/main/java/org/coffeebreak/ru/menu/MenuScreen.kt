package org.coffeebreak.ru.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.MyAsyncImage
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.b2
import org.coffeebreak.ru.theme.b3
import org.coffeebreak.ru.theme.darkBlue4
import org.coffeebreak.ru.theme.navMenu

data class Item(
    val image: Int,
    val title: String
)

@Composable
fun MenuScreen(navController: NavController, viewModel: MenuViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val items = listOf(
        Item(R.drawable.cofe, "Американо"),
        Item(R.drawable.cofe, "Капучино"),
        Item(R.drawable.cofe, "Латте"),
        Item(R.drawable.cofe, "Флэт Уайт"),
        Item(R.drawable.cofe, "Раф"),
        Item(R.drawable.cofe, "Эспрессо")
    )
    Column() {
        Spacer(Modifier.height(30.dp))
        Row(modifier = Modifier.padding(horizontal = 25.dp)) {
            Column {
                Text(
                    stringResource(R.string.welcome2),
                    style = MainTheme.typography.titleLarge,
                    color = b3
                )
                Text(
                    "Алексей",
                    style = MainTheme.typography.labelMedium
                )
            }
            Spacer(Modifier.weight(1f))
            MyIcon(icon = R.drawable.cart)
            MyIcon(
                icon = R.drawable.profile,
                modifier = Modifier.padding(start = 20.dp, end = 8.dp)
            )
        }
        Spacer(Modifier.height(15.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                .background(navMenu)
        ) {
            Text(stringResource(R.string.choose),
                modifier = Modifier.padding(start = 30.dp, top = 15.dp, bottom = 30.dp),
                style = MainTheme.typography.labelMedium,
                fontSize = 16.sp,
                color = b2
            )
            if (state.isLoading) {
                Text("Загрузка")
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp)
                ) {
                    items(state.coffees) { i ->

                        CoffeeCard(i.imageUrl, i.title)
                    }

                }
            }
        }
    }
}

@Composable
fun CoffeeCard(imageUrl: String, title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyAsyncImage(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 12.dp),
            imageUrl = imageUrl
        )
        Text(title, style = MainTheme.typography.titleMedium, color = darkBlue4)
        Spacer(Modifier.height(30.dp))
    }
}