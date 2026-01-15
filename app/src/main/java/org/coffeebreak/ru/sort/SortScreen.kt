package org.coffeebreak.ru.sort

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.IngredientItem
import org.coffeebreak.ru.utils.ObserveAction

@Composable
fun SortScreen(navController: NavController, viewModel: SortViewModel = hiltViewModel()) {
    IngredientItem(viewModel.state.collectAsState().value, viewModel.loading.collectAsState().value) {
        navController.navigate(Route.Constructor(sortId = it))
    }
    val con = LocalContext.current
    ObserveAction(viewModel.channel) {
        Toast.makeText(con, "ОШИБКА БЛЯТЬ $it", Toast.LENGTH_SHORT ).show()
    }
}