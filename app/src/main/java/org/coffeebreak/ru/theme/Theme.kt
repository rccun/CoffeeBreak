package org.coffeebreak.ru.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import org.coffeebreak.ru.theme.navMenu

data class CustomColorTheme(
    val bg: Color = Color.Unspecified,
    val logo: Color = Color.Unspecified,
    val authMedium: Color = Color.Unspecified,
    val authLarge: Color = Color.Unspecified,
    val authIcon: Color = Color.Unspecified,
    val authHint: Color = Color.Unspecified,
    val authTerms: Color = Color.Unspecified,
    val splashLarge: Color = Color.Unspecified,
    val splashMedium: Color = Color.Unspecified,
    val splashBox: Color = Color.Unspecified,
    val authIconPassword: Color = Color.Unspecified,
    val authForget: Color = Color.Unspecified,
    val authOtp: Color = Color.Unspecified,
    val authOtpText: Color = Color.Unspecified,
    val green: Color = green1,
    val chooseCoffee: Color = Color.Unspecified,
    val authTextField: Color = Color.Unspecified,
    val lazyColor: Color = Color.Unspecified,
    val default: Color = Color.Unspecified,
    val activeBottomIcon: Color = Color.Unspecified,
    val unactiveBottomIcon: Color = Color.Unspecified,
    val bottomNav: Color = Color.Unspecified,
    val icon: Color = Color.Unspecified,
    val titleText: Color = Color.Unspecified,
    val orderText: Color = Color.Unspecified,
    val unactiveRisColor: Color = Color.Unspecified,
    val activeOrderPickup: Color = Color.Unspecified,
    val orderCoast: Color = Color.Unspecified,
    val orderButton: Color = Color.Unspecified,
    val sliderTrack: Color = Color.Unspecified,
)

val LightColorScheme = CustomColorTheme(
    bg = bgW,
    logo = bgW,
    authMedium = blue3,
    authLarge = textColor,
    authIcon = green2,
    authHint = b3,
    authTerms = blue3,
    splashLarge = textColor,
    splashMedium = lightGray,
    splashBox = blue3.copy(alpha = 0.2f),
    authIconPassword = darkBlue4,
    authForget = green2,
    authOtp = Color(0xFFB7BBC9),
    authOtpText = blue3,
    chooseCoffee = b2,
    authTextField = Color(0xFFC1C7D0),
    lazyColor = navMenu,
    default = Color.Black,
    activeBottomIcon = blue3,
    unactiveBottomIcon = grayD8,
    bottomNav = Color.White,
    icon = darkBlue4,
    titleText = darkBlue4,
    orderText = Color(0xFF282828),
    unactiveRisColor = darkBlue4,
    activeOrderPickup = Color.Black,
    orderCoast = darkBlue4,
    orderButton = blue3,
    sliderTrack = Color(0xFF007AFF)
)
val DarkColorScheme = CustomColorTheme(
    bg = bgB,
    logo = bgW,
    authMedium = b3,
    authLarge = b1,
    authIcon = b1,
    authHint = b3,
    authTerms = b1,
    splashLarge = bgW,
    splashMedium = lightGray,
    splashBox = bgW.copy(alpha = 0.2f),
    authIconPassword = Color(0xFFA8A8A8),
    authForget = blue3,
    authOtp = Color(0xFF585A62),
    authOtpText = lightGray.copy(alpha = 0.5f),
    chooseCoffee = grayD8,
    authTextField = Color(0xFFC1C7D0),
    lazyColor = bg,
    default = Color.White,
    activeBottomIcon = Color(0xFF4F7993),
    unactiveBottomIcon = grayD8,
    bottomNav = navMenu,
    icon = b1,
    titleText = b2,
    orderText = b1,
    unactiveRisColor = bgW,
    activeOrderPickup = b1,
    orderCoast = Color(0xFF61ADDD),
    orderButton = bg,
    sliderTrack = b1,
)

val LocalCustomColorProvider = staticCompositionLocalOf { CustomColorTheme() }

@Composable
fun MyCoffeeBreakTheme(
    isSystemInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val theme = if (isSystemInDarkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
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


@Composable
fun CoffeeBreakTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
}