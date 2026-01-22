package org.coffeebreak.ru.profile

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.Route
import org.coffeebreak.ru.common.FeedBackOrder
import org.coffeebreak.ru.common.MyTopAppBar
import org.coffeebreak.ru.common.ProfileItem
import org.coffeebreak.ru.utils.ObserveAction

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {
    val state = viewModel.state.value
    val user = state.user
    //    val user = viewModel.user.collectAsState().value
//    Log.e("TAG", "$user: ");
//    val loading = viewModel.loading.collectAsState().value
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        MyTopAppBar(
            "Профиль",
            isBack = true,
            isCart = false,
            onBackClick = { navController.navigate(Route.Menu()) }) {
            if (state.isLoading/* && user != null*/) {
                Text("Downloading")
            } else {
                Spacer(Modifier.height(29.dp))
                ProfileItem(R.drawable.profile, "Имя", user!!.name)
                ProfileItem(R.drawable.phone_icon, "Phone number", user.phone)
                ProfileItem(R.drawable.email_icon, "Email", user.email)
                ProfileItem(R.drawable.address, "Адрес кофейни", user.address!!)
                ProfileItem(R.drawable.qr_icon, "QR-code", "Для получения заказа", false) {
                    navController.navigate(Route.QR)
                }
            }
            Spacer(Modifier.weight(1f))
            Text("ВЫЙТИ", fontSize = 50.sp, modifier = Modifier.clickable {
                viewModel.onLogOutClick()
                navController.navigate(Route.Splash)
            })
        }
    }
//    FeedBackOrder(3)
//    val con = LocalContext.current
//    ObserveAction(viewModel.channel) {
//        Toast.makeText(con, "ОШИБКА $it", Toast.LENGTH_SHORT).show()
//    }
}