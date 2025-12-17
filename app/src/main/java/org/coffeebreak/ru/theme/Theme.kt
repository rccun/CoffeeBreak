package org.coffeebreak.ru.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

data class CustomColorTheme(
    val bg: Color? = null,
    val logo: Color? = null,
    val authMedium: Color? = null,
    val authLarge: Color? = null,
    val authIcon: Color? = null,
    val authHint: Color? = null,
    val splashLarge: Color? = null,
    val splashMedium: Color? = null,
    val authIconPassword: Color? = null,
    val authForget: Color? = null,
    val authOtp: Color? = null,
    val authOtpText: Color? = null,


)
private val LightColorTheme = CustomColorTheme(
    bg = bgW,
    logo = bgW,
    authMedium = blue3,
    authLarge = textColor,
    authIcon = green2,
    authHint = b3,
    splashLarge = textColor,
    splashMedium = lightGray,
    authIconPassword = darkBlue4,
    authForget = green2,
    authOtp = Color(0xFFB7BBC9),
    authOtpText = blue3,
)
private val DarkColorTheme = CustomColorTheme(
    bg = bg,
    logo = bgW,
    authMedium = b3,
    authLarge = b1,
    authIcon = b1,
    authHint = b3,
    splashLarge = bgW,
    splashMedium = lightGray,
    authIconPassword = Color(0xFFA8A8A8),
    authForget = blue3,
    authOtp = Color(0xFF585A62),
    authOtpText = lightGray.copy(alpha = 0.5f),
)

val LocalCustomColorProvider = staticCompositionLocalOf { CustomColorTheme(TODO()) }

@Composable
fun MyCoffeeBreakTheme(
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val theme = if (isSystemInDarkTheme) {
        CustomColorTheme(Color.Black)
    } else {
        CustomColorTheme(Color.White)
    }

    CompositionLocalProvider(
        LocalCustomColorProvider provides theme,
        content = content
    )
}

object MainTheme {

    val colorScheme: CustomColorTheme
        @Composable
        get() = LocalCustomColorProvider.current

    val typography: androidx.compose.material3.Typography
        @Composable @ReadOnlyComposable
        get() = Typography
}

private val DarkColorScheme = darkColorScheme(
    background = bgB,
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    background = Color.White,
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CoffeeBreakTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

//object MainTheme {
//}