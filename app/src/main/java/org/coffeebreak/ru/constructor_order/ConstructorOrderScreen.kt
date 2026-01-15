package org.coffeebreak.ru.constructor_order

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.MyIcon
import org.coffeebreak.ru.theme.MainTheme

//@Composable
//fun ConstructorOrderScreen(
//    navController: NavController,
//    viewModel: ConstructorOrderViewModel = hiltViewModel()
//) {
//    val state = viewModel.state.value
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(horizontal = 25.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 20.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            MyIcon(
//                icon = R.drawable.back,
//                tintColor = MainTheme.colorScheme.icon) {
//                if (state.page == 3) {
//                    viewModel.onEvent(ConstructorOrderEvents.OnBackCLick)
//                } else {
//                    navController.popBackStack()
//                }
//            }
//
//            Text(
//                stringResource(R.string.cons_order),
//                color = MainTheme.colorScheme.titleText,
//                style = MainTheme.typography.labelMedium,
//                fontSize = 16.sp
//            )
//            MyIcon(icon = R.drawable.cart, tintColor = MainTheme.colorScheme.icon)
//        }
//        Text(
//            when (state.page) {
//                1 -> {
//                    stringResource(R.string.choose_barista)
//                }
//
//                2 -> {
//                    stringResource(R.string.choose_country)
//                }
//
//                3 -> {
//                    stringResource(R.string.choose_sort)
//                }
//
//                4 -> {
//                    stringResource(R.string.choose_supplement)
//                }
//
//                else -> {
//                    ""
//                }
//            },
//            style = MainTheme.typography.chooseBarista,
//            color = MainTheme.colorScheme.icon
//        )
//        Spacer(Modifier.height(17.dp))
//
//        LaunchedEffect(Unit) {
//            viewModel.loadData()
//        }
//        if (state.isLoading) {
//            Text("Downloading")
//        } else {
//            when (state.page) {
//                1 -> {
//                    BaristaScreen(state.baristas, viewModel, navController)
//                }
//
//                2 -> {
//                    ItemScreen(state.countries) {
//                        viewModel.onEvent(ConstructorOrderEvents.OnCountryClick(it))
//                    }
//                }
//
//                3 -> {
//                    ItemScreen(state.sorts) {
//                        viewModel.onEvent(ConstructorOrderEvents.OnSortClick(it))
//                        navController.popBackStack()
//                    }
//                }
//
//                4 -> {
//                    ItemScreen(state.supplements) {
//                        viewModel.onEvent(ConstructorOrderEvents.OnSupplementClick(it))
//                        navController.popBackStack()
//                    }
//                }
//            }
//        }
//    }
//}
