package org.coffeebreak.ru.constructor_order

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.coffeebreak.domain.model.BaristaModel
import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.MyAsyncImage
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.green3
import org.coffeebreak.ru.theme.red

@Composable
fun ConstructorOrderScreen(
    navController: NavController,
    viewModel: ConstructorOrderViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            MyIcon(
                icon = R.drawable.back,
                tintColor = MainTheme.colorScheme.icon,
                modifier = Modifier.clickable {
                    if (state.page > 1) {
                        viewModel.onEvent(ConstructorOrderEvents.OnBackCLick)
                    } else {
                        navController.navigate(Route.Constructor)
                    }
                })
            Text(
                stringResource(R.string.cons_order),
                color = MainTheme.colorScheme.titleText,
                style = MainTheme.typography.labelMedium,
                fontSize = 16.sp
            )
            MyIcon(icon = R.drawable.cart, tintColor = MainTheme.colorScheme.icon)
        }
        Text(
            when (state.page) {
                1 -> {
                    stringResource(R.string.choose_barista)
                }

                2 -> {
                    stringResource(R.string.choose_country)
                }

                3 -> {
                    stringResource(R.string.choose_sort)
                }

                4 -> {
                    stringResource(R.string.choose_supplement)
                }

                else -> {
                    ""
                }
            },
            style = MainTheme.typography.chooseBarista,
            color = MainTheme.colorScheme.icon
        )
        Spacer(Modifier.height(17.dp))

        LaunchedEffect(Unit) {
            viewModel.loadData()
        }
        if (state.isLoading) {
            Text("Downloading")
        } else {
            when (state.page) {
                1 -> {
                    BaristaScreen(state.baristas, viewModel)
                }

                2 -> {
                    ItemScreen(state.countries,) {
//                        viewModel.onEvent(On)
                    }
                }

                3 -> {
                    ItemScreen(state.sorts, ) {

                    }
                }

                4 -> {
                    ItemScreen(state.supplements, ) {

                    }
                }
            }
        }
    }
}

@Composable
fun BaristaScreen(baristas: List<BaristaModel>, viewModel: ConstructorOrderViewModel) {
    Column {
        baristas.forEach { i ->
            BaristaItem(i.avatarUrl, i.name, i.skill, i.status) {
                viewModel.onEvent(ConstructorOrderEvents.OnBaristaClick(i.id))

            }
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun BaristaItem(
    avatarUrl: String,
    name: String,
    skill: String,
    status: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(MainTheme.colorScheme.baristaItem)
            .clickable {

                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        MyAsyncImage(
            imageUrl = avatarUrl,
            modifier = Modifier
                .padding(9.dp)
                .size(62.dp)
                .clip(RoundedCornerShape(16.dp)),
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                name,
                style = MainTheme.typography.displayMedium,
                color = MainTheme.colorScheme.default,
                fontSize = 15.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                skill,
                color = MainTheme.colorScheme.baristaSkill,
                style = MainTheme.typography.displayMedium,
                fontSize = 14.sp
            )

        }
        Spacer(Modifier.weight(1f))
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(15.dp)
                .background(
                    if (status) {
                        green3
                    } else {
                        red
                    }
                )
        )
        Spacer(Modifier.width(34.dp))
    }
}

@Composable
fun ItemScreen(items: List<ItemModel>, onClick: (String) -> Unit) { // 20 dp
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(27.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(items) { i ->
            Column(
                modifier = Modifier.clickable{
                    onClick(i.id)
                }
            ) {
                MyAsyncImage(
                    imageUrl = i.imageUrl,
                    modifier = Modifier
                        .padding(bottom = 7.dp)
                        .fillMaxWidth()
//                        .size(158.dp)
                        .clip(RoundedCornerShape(20.dp)),
                )
                Text(
                    i.title,
                    style = MainTheme.typography.countryTitle,
                    color = MainTheme.colorScheme.default
                )
                Spacer(Modifier.height(7.dp))
                Text(
                    i.description,
                    style = MainTheme.typography.countryTitle,
                    fontSize = 10.sp,
                    color = MainTheme.colorScheme.countryTitle
                )
            }
        }
    }
}