package org.coffeebreak.ru.country

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
fun CountryScreen(navController: NavController, viewModel: CountryViewModel = hiltViewModel()) {
    IngredientItem(viewModel.state.collectAsState().value, viewModel.loading.collectAsState().value) {
        navController.navigate(Route.Sort)
    }
    val con = LocalContext.current
    ObserveAction(viewModel.channel) {
        Toast.makeText(con, "ОШИБКА БЛЯТЬ $it", Toast.LENGTH_SHORT ).show()
    }
}