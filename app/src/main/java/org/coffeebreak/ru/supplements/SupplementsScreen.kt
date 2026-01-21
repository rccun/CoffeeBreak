package org.coffeebreak.ru.supplements

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.IngredientItem
import org.coffeebreak.ru.common.MyTopAppBar
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.utils.ObserveAction

@Composable
fun SupplementsScreen(
    navController: NavController,
    viewModel: SupplementsViewModel = hiltViewModel()
) {
    MyTopAppBar(
        stringResource(R.string.cons_order),
        isCart = false,
        onBackClick = {
            navController.navigate(Route.Constructor)
        }
    ) {
        Text(
            stringResource(R.string.choose_supplement),
            style = MainTheme.typography.chooseBarista,
            color = MainTheme.colorScheme.icon
        )
        Spacer(Modifier.height(17.dp))
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