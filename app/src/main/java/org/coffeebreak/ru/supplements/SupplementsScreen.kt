package org.coffeebreak.ru.supplements

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.IngredientItem
import org.coffeebreak.ru.common.MyTopAppBar
import org.coffeebreak.ru.utils.ObserveAction

@Composable
fun SupplementsScreen(
    navController: NavController,
    viewModel: SupplementsViewModel = hiltViewModel()
) {
    MyTopAppBar(
        stringResource(R.string.choose_supplement), onBackClick = {
            navController.navigate(Route.Constructor)
        }, {}
    ) {
        val supplements by viewModel.state.collectAsStateWithLifecycle()
        val isLoading by viewModel.loading.collectAsStateWithLifecycle()
        IngredientItem(
            supplements,
            !isLoading
        ) {
            Route.Constructor.baristaId = it

            navController.navigate(Route.Constructor)
        }
    }

    val con = LocalContext.current
    ObserveAction(viewModel.channel) {
        Toast.makeText(con, "ОШИБКА БЛЯТЬ $it", Toast.LENGTH_SHORT).show()
    }
}