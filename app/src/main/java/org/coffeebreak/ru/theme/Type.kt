package org.coffeebreak.ru.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.coffeebreak.ru.R

// Set of Material typography styles to start with
val font = FontFamily(
    Font(R.font.r_r, FontWeight.Normal)
)
val fontP = FontFamily(
    Font(R.font.poppins_r, FontWeight.Normal),
    Font(R.font.poppins_sb, FontWeight.SemiBold),
)
val fontDM = FontFamily(
    Font(R.font.dm_m, FontWeight.Medium)
)
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = font,
        fontWeight = FontWeight.Normal,
        fontSize = 64.sp,
//        color = MainTheme.colorScheme.logo
    ),
    displayMedium = TextStyle(
        fontFamily = fontP,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
//        color = b3
    ),
    bodyLarge = TextStyle(
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
//        color = textColor
    ),
    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
//        color = blue3
    ),
    titleLarge = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
//        color = Color.White
    ),
    labelMedium = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
//        color = b1
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
    bodySmall = TextStyle(
        fontFamily = fontP,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)