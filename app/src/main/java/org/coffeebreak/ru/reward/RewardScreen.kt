package org.coffeebreak.ru.reward

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun RewardScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()/*, horizontalAlignment = Alignment.CenterHorizontally*/) {
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
    }
}