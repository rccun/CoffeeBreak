package org.coffeebreak.ru.barista

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import org.coffeebreak.ru.common.BaristaItem
import org.coffeebreak.ru.common.MyDialog
import org.coffeebreak.ru.common.MyTopAppBar
import org.coffeebreak.ru.utils.ObserveAction

@Composable
fun BaristaScreen(
    navController: NavController,
    viewModel: BaristaViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loading.collectAsStateWithLifecycle()
    val baristas by viewModel.state.collectAsStateWithLifecycle()
    MyTopAppBar(
        stringResource(R.string.choose_barista), onBackClick = {
            navController.popBackStack()
        }, {}
    ) {
        if (isLoading) {
            Text("Downloading")
        } else {
            baristas.forEach { i ->
                BaristaItem(i.avatarUrl, i.name, i.skill, i.status) {
                    Route.Constructor.baristaId = i.id
                    navController.navigate(Route.Constructor)
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }

    val con = LocalContext.current
    ObserveAction(viewModel.channel) {
        Toast.makeText(con, "ОШИБКА БЛЯТЬ $it", Toast.LENGTH_SHORT).show()
    }
}
