package org.coffeebreak.ru.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.coffeebreak.ru.R

@Composable
fun MyAsyncImage(modifier: Modifier = Modifier, imageUrl: String, contentScale: ContentScale = ContentScale.FillWidth) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = contentScale,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.error),
        modifier = modifier
            .fillMaxWidth()
    )
}