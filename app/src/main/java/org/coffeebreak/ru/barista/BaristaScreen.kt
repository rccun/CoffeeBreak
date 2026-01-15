package org.coffeebreak.ru.barista

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.BaristaItem
import org.coffeebreak.ru.utils.ObserveAction

@Composable
fun BaristaScreen(
    navController: NavController,
    viewModel: BaristaViewModel = hiltViewModel()
) {
    Column {
        viewModel.state.collectAsState().value.forEach { i ->
            BaristaItem(i.avatarUrl, i.name, i.skill, i.status) {

                navController.navigate(Route.Constructor(baristaId = i.id))
            }
            Spacer(Modifier.height(20.dp))
        }
    }
    val con = LocalContext.current
    ObserveAction(viewModel.channel) {
        Toast.makeText(con, "ОШИБКА БЛЯТЬ $it", Toast.LENGTH_SHORT).show()
    }
}
