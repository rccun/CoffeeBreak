package org.coffeebreak.ru

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yandex.mapkit.MapKitFactory
import dagger.hilt.android.AndroidEntryPoint
import org.coffeebreak.ru.cafemap.CafeMapScreen
import org.coffeebreak.ru.login.LoginScreen
import org.coffeebreak.ru.main.MainScreen
import org.coffeebreak.ru.menu.MenuScreen
import org.coffeebreak.ru.signup.SignUpScreen
import org.coffeebreak.ru.splash.SplashScreen
import org.coffeebreak.ru.startup.StartUpScreen
import org.coffeebreak.ru.theme.CoffeeBreakTheme
import org.coffeebreak.ru.theme.MyCoffeeBreakTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MyCoffeeBreakTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Route.Menu,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Route.Main> {
                            MainScreen(navController)
                        }
                        composable<Route.Splash> {
                            SplashScreen(navController)
                        }
                        composable<Route.Login> {
                            LoginScreen(navController)
                        }
                        composable<Route.SignUp> {
                            SignUpScreen(navController)
                        }
                        composable<Route.StartUp> {
                            StartUpScreen(navController)
                        }
                        composable<Route.Cafe> {
                            CafeMapScreen(navController)
                        }
                        composable<Route.Menu> {
                            MenuScreen(navController)
                        }
                    }
                }
            }
        }
    }
}