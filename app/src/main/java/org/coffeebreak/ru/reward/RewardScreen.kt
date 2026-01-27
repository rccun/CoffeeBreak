package org.coffeebreak.ru.reward

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.common.LoyaltyCard
import org.coffeebreak.ru.common.PointsCard
import org.coffeebreak.ru.common.PointsRow
import org.coffeebreak.ru.theme.MainTheme
import org.coffeebreak.ru.theme.blue3

@Composable
fun RewardScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp)/*, horizontalAlignment = Alignment.CenterHorizontally*/
    ) {
        Spacer(Modifier.height(21.dp))

        Text(
            stringResource(R.string.reward),
            color = MainTheme.colorScheme.titleText,
            style = MainTheme.typography.labelMedium,
            fontSize = 16.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.height(26.dp))
        LoyaltyCard(4)
        Spacer(Modifier.height(24.dp))
        PointsCard()
        Text(
            stringResource(R.string.history_points), modifier = Modifier.padding(vertical = 8.dp/*, bottom = 24.dp*/),
            style = MainTheme.typography.chooseBarista, fontSize = 14.sp, color = blue3
        )

        LazyColumn {
            items(10) {

                PointsRow("Американо", "24 june", "12:30")
            }
        }
    }
}