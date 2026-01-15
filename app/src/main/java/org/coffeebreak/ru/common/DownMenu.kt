package org.coffeebreak.ru.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.coffeebreak.ru.R
import org.coffeebreak.ru.theme.MainTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownMenu(
    items: List<String>,
    title: String,
    item: String,
    onCancelClick: () -> Unit,
    onClick: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Spacer(Modifier.weight(1f))
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(13.dp))
                .background(MainTheme.colorScheme.downMenu)
                .fillMaxWidth()
        ) {
            Text(
                title,
                style = MainTheme.typography.displayMedium,
                fontSize = 13.sp,
                color = MainTheme.colorScheme.downMenuTitle,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 12.dp)
            )

            items.forEach { i ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClick(i) },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        i.toString(),
                        style = MainTheme.typography.displayMedium,
                        fontSize = 20.sp,
                        color = if (i == item) {
                            MainTheme.colorScheme.activeOrderPickup
                        } else {
                            MainTheme.colorScheme.downMenuItem
                        },
                        modifier = Modifier.padding(vertical = 18.dp)
                    )
                }
            }
        }
        Spacer(Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(13.dp))
                .background(
                    MainTheme.colorScheme.downMenuCancel
                )
                .clickable {
                    onCancelClick()
                }
                .padding(vertical = 18.dp)
        )
        {
            Text(
                stringResource(R.string.cancel),
                modifier = Modifier.align(Alignment.Center),
                style = MainTheme.typography.labelLarge,
                color = MainTheme.colorScheme.unactiveRisColor // db4 white
            )
        }
        Spacer(Modifier.height(32.dp))
    }
}