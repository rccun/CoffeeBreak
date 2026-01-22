package org.coffeebreak.ru.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.R

data class Typo(
    val type: TextStyle = TextStyle.Default,
)

data class MyTypo(
    val displayLarge: TextStyle = TextStyle.Default,
    val displayMedium: TextStyle = TextStyle.Default,
    val displaySmall: TextStyle = TextStyle.Default,
    val headlineLarge: TextStyle = TextStyle.Default,
    val headlineMedium: TextStyle = TextStyle.Default,
    val headlineSmall: TextStyle = TextStyle.Default,
    val titleLarge: TextStyle = TextStyle.Default,
    val titleMedium: TextStyle = TextStyle.Default,
    val titleSmall: TextStyle = TextStyle.Default,
    val bodyLarge: TextStyle = TextStyle.Default,
    val bodyMedium: TextStyle = TextStyle.Default,
    val bodySmall: TextStyle = TextStyle.Default,
    val labelLarge: TextStyle = TextStyle.Default,
    val labelMedium: TextStyle = TextStyle.Default,
    val labelSmall: TextStyle = TextStyle.Default,
    val chooseBarista: TextStyle = TextStyle.Default,
    val countryTitle: TextStyle = TextStyle.Default,
)

// Set of Material typography styles to start with
val font = FontFamily(
    Font(R.font.r_r, FontWeight.Normal)
)
val fontP = FontFamily(
    Font(R.font.poppins_r, FontWeight.Normal),
    Font(R.font.poppins_m, FontWeight.Medium),
    Font(R.font.poppins_sb, FontWeight.SemiBold),
    Font(R.font.poppins_b, FontWeight.Bold),
)
val fontDM = FontFamily(
    Font(R.font.dm_m, FontWeight.Medium),
    Font(R.font.dm_r, FontWeight.Normal),
)

val Typography = MyTypo(
    displayLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 64.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = fontP,
        fontWeight = FontWeight.Normal
    ),
    labelLarge  = TextStyle(
        fontFamily = fontP,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    chooseBarista = TextStyle(
        fontFamily = fontP,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = fontP,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
    ),
    bodyLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
    ),
    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    ),
    titleLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    labelMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
    ),
    displaySmall = TextStyle(
        fontFamily = fontDM,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = fontDM,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = fontDM,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),
    countryTitle = TextStyle(
        fontFamily = fontDM,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
    ),
)