package org.coffeebreak.ru.sort

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.IngredientItem
import org.coffeebreak.ru.common.MyTopAppBar
import org.coffeebreak.ru.utils.ObserveAction

@Composable
fun SortScreen(navController: NavController, viewModel: SortViewModel = hiltViewModel()) {
    MyTopAppBar(
        stringResource(R.string.choose_sort), onBackClick = {
            navController.navigate(Route.Country)
        }, {}
    ) {
        IngredientItem(
            viewModel.state.collectAsState().value,
            viewModel.loading.collectAsState().value
        ) {
            Route.Constructor.baristaId = it
            navController.navigate(Route.Constructor)
        }
    }
    val con = LocalContext.current
    ObserveAction(viewModel.channel) {
        Toast.makeText(con, "ОШИБКА БЛЯТЬ $it", Toast.LENGTH_SHORT ).show()
    }
}