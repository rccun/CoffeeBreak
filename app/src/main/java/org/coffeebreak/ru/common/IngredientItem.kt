package org.coffeebreak.ru.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.coffeebreak.domain.model.ItemModel
import org.coffeebreak.ru.theme.MainTheme

@Composable
fun IngredientItem(items: List<ItemModel>, isShow: Boolean, onClick: (String) -> Unit) {
    if (isShow) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(27.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(items) { i ->
                Column(
                    modifier = Modifier.clickable {
                        onClick(i.id)
                    }
                ) {
                    MyAsyncImage(
                        imageUrl = i.imageUrl,
                        modifier = Modifier
                            .padding(bottom = 7.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp)),
                    )
                    Text(
                        i.title,
                        style = MainTheme.typography.countryTitle,
                        color = MainTheme.colorScheme.default
                    )
                    Spacer(Modifier.height(7.dp))
                    Text(
                        i.description,
                        style = MainTheme.typography.countryTitle,
                        fontSize = 10.sp,
                        color = MainTheme.colorScheme.countryTitle
                    )
                }
            }
        }
    }
}